package de.rumpold.xplanedb.parser;

import de.rumpold.xplanedb.exceptions.ParseException;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;

/**
 * Created by Adriano on 14.07.2015.
 */
public abstract class NavEntryParser {
    public abstract NavEntryType[] getAcceptedTypes();

    public abstract NavEntry parseLine(String line) throws ParseException;

    protected final boolean validateType(NavEntryType type) {
        for (NavEntryType acceptedType : getAcceptedTypes()) {
            if (acceptedType == type) {
                return true;
            }
        }
        return false;
    }
}
