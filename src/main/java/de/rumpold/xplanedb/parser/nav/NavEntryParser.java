package de.rumpold.xplanedb.parser.nav;

import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import de.rumpold.xplanedb.parser.exceptions.EntryTooShortException;
import de.rumpold.xplanedb.parser.exceptions.InvalidDummyValueException;
import de.rumpold.xplanedb.parser.exceptions.ParseException;

import java.util.Arrays;

/**
 * Created by Adriano on 14.07.2015.
 */
public abstract class NavEntryParser {
    public abstract NavEntryType[] getAcceptedTypes();
    protected abstract int getFieldCount();

    protected String concatFields(String[] fields, int startIndex) {
        return String.join(" ", Arrays.copyOfRange(fields, startIndex, fields.length));
    }

    protected String concatNameFields(String[] fields) {
        return concatFields(fields, getFieldCount() - 1);
    }

    protected abstract NavEntry parseEntry(String[] fields) throws ParseException, NumberFormatException;
    public NavEntry parseLine(String line) throws ParseException {
        try {
            final String[] fields = line.split("[ ]+");
            if (fields.length < getFieldCount()) {
                throw new EntryTooShortException();
            }
            final NavEntryType type = NavEntryType.fromValue(Integer.parseInt(fields[0]));
            validateType(type);

            return parseEntry(fields);
        } catch (NumberFormatException e) {
            throw new ParseException(getClass().getSimpleName() + " illegal numeric value in line: " + line, e);
        } catch (ParseException e) {
            System.err.println(e.getClass().getSimpleName() + " in line: " + line);
            throw e;
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
