package de.rumpold.xplanedb.parser;

import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import de.rumpold.xplanedb.parser.exceptions.InvalidHeaderException;
import de.rumpold.xplanedb.parser.exceptions.ParseException;

import java.io.*;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Created by Adriano on 14.07.2015.
 */
public class NavDbParser {
    private static final String NAV_DB_HEADER = "I";
    private static final String NAV_DB_FOOTER = "99";

    private final Map<NavEntryType, NavEntryParser> parsers = new TreeMap<>();

    private void registerParser(NavEntryParser parser) {
        for (NavEntryType acceptedType : parser.getAcceptedTypes()) {
            parsers.put(acceptedType, parser);
        }
    }

    {
        registerParser(new NdbEntryParser());
        registerParser(new VorEntryParser());
        registerParser(new LocalizerEntryParser());
        registerParser(new GlideslopeEntryParser());
    }

    public void parseFile(String navDb) throws ParseException {
        final File dbFile = new File(navDb);

        try {
            BufferedReader in = new BufferedReader(new FileReader(dbFile));

            String line = in.readLine();
            if (!NAV_DB_HEADER.equals(line)) {
                throw new InvalidHeaderException("Invalid header magic value: " + line);
            }

            // Version information line, skip for now
            line = in.readLine();

            for (line = in.readLine(); !NAV_DB_FOOTER.equals(line); line = in.readLine()) {
                if (line.isEmpty()) {
                    continue;
                }

                final StringTokenizer tokenizer = new StringTokenizer(line, " ");

                try {
                    final NavEntryType type = NavEntryType.fromValue(Integer.parseInt(tokenizer.nextToken()));
                    final NavEntryParser parser = parsers.get(type);
                    if (parser != null) {
                        final NavEntry entry = parser.parseLine(line);

                        if (entry != null) {
//                            System.out.println(entry);
                        }
                    } else {
//                        System.err.println("No parser for entry type " + type);
                    }
                } catch (NoSuchElementException e) {
                    throw new ParseException("Unexpected end of line while reading navigation database: " + line, e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new ParseException("Cannot open navigation database for reading: " + navDb, e);
        } catch (IOException e) {
            throw new ParseException("I/O error while reading navigation database: " + navDb, e);
        }
    }
}
