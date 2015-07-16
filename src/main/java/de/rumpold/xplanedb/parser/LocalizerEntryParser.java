package de.rumpold.xplanedb.parser;

import de.rumpold.xplanedb.model.LocalizerEntry;
import de.rumpold.xplanedb.model.LocalizerEntry.LocalizerType;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import de.rumpold.xplanedb.parser.exceptions.ParseException;

/**
 * Created by Adriano on 14.07.2015.
 */
public final class LocalizerEntryParser extends NavEntryParser {
    @Override
    public NavEntryType[] getAcceptedTypes() {
        return new NavEntryType[]{NavEntryType.ILS_LOC, NavEntryType.LOC};
    }

    @Override
    protected int getFieldCount() {
        return 11;
    }

    @Override
    public NavEntry parseEntry(String[] fields) throws ParseException {
        final NavEntryType entryType = NavEntryType.fromValue(Integer.parseInt(fields[0]));
        final LocalizerType type;
        if (entryType == NavEntryType.LOC) {
            type = LocalizerType.STANDALONE;
        } else if (entryType == NavEntryType.ILS_LOC) {
            type = LocalizerType.ILS;
        } else {
            throw new ParseException("Unknown localizer type");
        }

        final double latitude = Double.parseDouble(fields[1]);
        final double longitude = Double.parseDouble(fields[2]);
        final int elevation = Integer.parseInt(fields[3]);
        final int frequency = Integer.parseInt(fields[4]);
        final int range = Integer.parseInt(fields[5]);
        final double bearing = Double.parseDouble(fields[6]);
        final String identifier = fields[7];
        final String airportCode = fields[8];
        final String runwayNumber = fields[9];
        final String name = concatNameFields(fields);

        return new LocalizerEntry(type, latitude, longitude, elevation, frequency, range, bearing, identifier, airportCode, runwayNumber, name);
    }
}
