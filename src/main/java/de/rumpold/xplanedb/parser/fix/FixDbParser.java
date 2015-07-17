package de.rumpold.xplanedb.parser.fix;

import de.rumpold.xplanedb.model.Fix;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.parser.exceptions.EntryTooShortException;
import de.rumpold.xplanedb.parser.exceptions.InvalidHeaderException;
import de.rumpold.xplanedb.parser.exceptions.ParseException;
import de.rumpold.xplanedb.parser.exceptions.PrematureEofException;
import de.rumpold.xplanedb.parser.nav.NavEntryParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Created by Adriano on 17.07.2015.
 */
public class FixDbParser {
    private static final int FIELD_COUNT = 3;
    private static final String FIX_DB_HEADER = "I";
    private static final String FIX_DB_FOOTER = "99";

    public List<Fix> parseFile(String fixDb) throws ParseException {
        final File dbFile = new File(fixDb);
        final List<Fix> result = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(dbFile));

            String line = in.readLine();
            if (!FIX_DB_HEADER.equals(line)) {
                throw new InvalidHeaderException("Invalid header magic value: " + line);
            }

            // Version information line, skip for now
            line = in.readLine();

            for (line = in.readLine(); !FIX_DB_FOOTER.equals(line); line = in.readLine()) {
                if (!in.ready()) {
                    throw new PrematureEofException();
                }

                if (line.isEmpty()) {
                    continue;
                }

                final StringTokenizer tokenizer = new StringTokenizer(line, " ");

                try {
                    final Fix entry = parseLine(line);
                    if (entry != null) {
                        result.add(entry);
                    }
                } catch (NoSuchElementException e) {
                    throw new ParseException("Unexpected end of line while reading navigation database: " + line, e);
                }
            }

            return result;
        } catch (FileNotFoundException e) {
            throw new ParseException("Cannot open navigation database for reading: " + fixDb, e);
        } catch (IOException e) {
            throw new ParseException("I/O error while reading navigation database: " + fixDb, e);
        }
    }

    protected Fix parseLine(String line) throws ParseException {
        try {
            final String[] fields = line.trim().split("[ ]+");
            if (fields.length != FIELD_COUNT) {
                throw new EntryTooShortException();
            }

            final double latitude = Double.parseDouble(fields[0]);
            final double longitude = Double.parseDouble(fields[1]);
            final String identifier = fields[2];

            return new Fix(latitude, longitude, identifier);
        } catch (NumberFormatException e) {
            throw new ParseException(getClass().getSimpleName() + " illegal numeric value in line: " + line, e);
        } catch (ParseException e) {
            System.err.println(e.getClass().getSimpleName() + " in line: " + line);
            throw e;
        }
    }
}
