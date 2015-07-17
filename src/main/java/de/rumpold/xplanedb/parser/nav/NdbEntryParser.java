package de.rumpold.xplanedb.parser.nav;

import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import de.rumpold.xplanedb.model.NdbEntry;
import de.rumpold.xplanedb.parser.exceptions.InvalidDummyValueException;
import de.rumpold.xplanedb.parser.exceptions.ParseException;

/**
 * Created by Adriano on 14.07.2015.
 */
public final class NdbEntryParser extends NavEntryParser {
    @Override
    public NavEntryType[] getAcceptedTypes() {
        return new NavEntryType[]{NavEntryType.NDB};
    }

    @Override
    protected int getFieldCount() {
        return 9;
    }

    @Override
    public NavEntry parseEntry(String[] fields) throws ParseException {
        final double latitude = Double.parseDouble(fields[1]);
        final double longitude = Double.parseDouble(fields[2]);
        final int elevation = Integer.parseInt(fields[3]);
        final int frequency = Integer.parseInt(fields[4]);
        final int range = Integer.parseInt(fields[5]);
        final double _dummy = Double.parseDouble(fields[6]);
        if (Math.abs(_dummy) > 1e-6) {
            throw new InvalidDummyValueException();
        }
        final String identifier = fields[7];
        final String name = concatNameFields(fields);

        return new NdbEntry(latitude, longitude, elevation, frequency, range, identifier, name);
    }
}
