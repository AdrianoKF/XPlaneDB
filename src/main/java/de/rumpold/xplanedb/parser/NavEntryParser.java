package de.rumpold.xplanedb.parser;

import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import de.rumpold.xplanedb.parser.exceptions.InvalidDummyValueException;
import de.rumpold.xplanedb.parser.exceptions.ParseException;

import java.util.Arrays;

/**
 * Created by Adriano on 14.07.2015.
 */
public abstract class NavEntryParser {
    public abstract NavEntryType[] getAcceptedTypes();
    protected abstract int getFieldCount();

    protected String concatNameFields(String[] fields) {
        return String.join(" ", Arrays.copyOfRange(fields, getFieldCount() - 1, getFieldCount() + 1));
    }

    protected abstract NavEntry parseEntry(String[] fields) throws ParseException, NumberFormatException;
    public NavEntry parseLine(String line) throws ParseException {
        try {
            final String[] fields = line.split("[ ]+");
            if (fields.length < getFieldCount()) {
                System.err.printf("Invalid number of fields (got %d, expected %d) in line: %s%n", fields.length, getFieldCount(), line);
                return null;
            }
            final NavEntryType type = NavEntryType.fromValue(Integer.parseInt(fields[0]));
            validateType(type);

            return parseEntry(fields);
        } catch (NumberFormatException e) {
            throw new ParseException(getClass().getSimpleName() + " illegal numeric value in line: " + line, e);
        } catch (InvalidDummyValueException e) {
            throw new ParseException(getClass().getSimpleName() + " dummy value must be 0.0 in line: " + line);
        }
    }

    protected final boolean validateType(NavEntryType type) {
        for (NavEntryType acceptedType : getAcceptedTypes()) {
            if (acceptedType == type) {
                return true;
            }
        }
        return false;
    }
}
