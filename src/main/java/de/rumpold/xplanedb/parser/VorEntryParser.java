package de.rumpold.xplanedb.parser;

import de.rumpold.xplanedb.exceptions.ParseException;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import de.rumpold.xplanedb.model.NdbEntry;
import de.rumpold.xplanedb.model.VorEntry;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Created by Adriano on 14.07.2015.
 */
public final class VorEntryParser extends NavEntryParser {
    @Override
    public NavEntryType[] getAcceptedTypes() {
        return new NavEntryType[]{NavEntryType.VOR};
    }

    @Override
    public NavEntry parseLine(String line) throws ParseException {
        final StringTokenizer tokenizer = new StringTokenizer(line, " ");

        try {
            final NavEntryType type = NavEntryType.fromValue(Integer.parseInt(tokenizer.nextToken()));
            validateType(type);
            final double latitude = Double.parseDouble(tokenizer.nextToken());
            final double longitude = Double.parseDouble(tokenizer.nextToken());
            final int elevation = Integer.parseInt(tokenizer.nextToken());
            final int frequency = Integer.parseInt(tokenizer.nextToken());
            final int range = Integer.parseInt(tokenizer.nextToken());
            final double variation = Double.parseDouble(tokenizer.nextToken());
            final String identifier = tokenizer.nextToken();
            final String name = tokenizer.nextToken();

            return new VorEntry(latitude, longitude, elevation, frequency, range, variation, identifier, name);
        } catch (NumberFormatException e) {
            throw new ParseException(getClass().getSimpleName() + " illegal numeric value in line: " + line, e);
        } catch (NoSuchElementException e) {
            throw new ParseException(getClass().getSimpleName() + " premature end of line: " + line, e);
        }
    }
}
