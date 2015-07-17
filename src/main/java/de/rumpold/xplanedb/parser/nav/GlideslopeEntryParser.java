package de.rumpold.xplanedb.parser.nav;

import de.rumpold.xplanedb.model.GlideslopeEntry;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import de.rumpold.xplanedb.parser.exceptions.ParseException;

/**
 * Created by Adriano on 14.07.2015.
 */
public final class GlideslopeEntryParser extends NavEntryParser {
    @Override
    public NavEntryType[] getAcceptedTypes() {
        return new NavEntryType[]{NavEntryType.GLIDESLOPE};
    }

    @Override
    protected int getFieldCount() {
        return 11;
    }

    @Override
    public NavEntry parseEntry(String[] fields) throws ParseException {
        final double latitude = Double.parseDouble(fields[1]);
        final double longitude = Double.parseDouble(fields[2]);
        final int elevation = Integer.parseInt(fields[3]);
        final int frequency = Integer.parseInt(fields[4]);
        final int range = Integer.parseInt(fields[5]);
        final double localizerBearing = Double.parseDouble(fields[6]);
        final String identifier = fields[7];
        final String airportCode = fields[8];
        final String runwayNumber = fields[9];
        final String name = concatNameFields(fields);

        return new GlideslopeEntry(latitude, longitude, elevation, frequency, range, localizerBearing, identifier, airportCode, runwayNumber, name);
    }
}
