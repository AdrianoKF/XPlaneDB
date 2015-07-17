package de.rumpold.xplanedb.parser.nav;

import de.rumpold.xplanedb.model.MarkerEntry;
import de.rumpold.xplanedb.model.MarkerEntry.MarkerType;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import de.rumpold.xplanedb.parser.exceptions.InvalidDummyValueException;
import de.rumpold.xplanedb.parser.exceptions.ParseException;

/**
 * Created by Adriano on 16.07.2015.
 */
public final class MarkerEntryParser extends NavEntryParser {
    public static final String EMPTY_IDENTIFIER = "----";

    @Override
    public NavEntryType[] getAcceptedTypes() {
        return new NavEntryType[]{NavEntryType.ILS_OM, NavEntryType.ILS_MM, NavEntryType.ILS_IM};
    }

    @Override
    protected int getFieldCount() {
        return 11;
    }

    @Override
    protected NavEntry parseEntry(String[] fields) throws ParseException, NumberFormatException {
        final NavEntryType entryType = NavEntryType.fromValue(Integer.parseInt(fields[0]));
        final MarkerType markerType;
        switch (entryType) {
            case ILS_IM:
                markerType = MarkerType.INNER;
                break;
            case ILS_MM:
                markerType = MarkerType.MIDDLE;
                break;
            case ILS_OM:
                markerType = MarkerType.OUTER;
                break;
            default:
                throw new ParseException("Invalid marker type");
        }

        // Validate dummy fields
        if (Integer.parseInt(fields[4]) != 0) {
            throw new InvalidDummyValueException();
        }
        if (Integer.parseInt(fields[5]) != 0) {
            throw new InvalidDummyValueException();
        }
        if (!EMPTY_IDENTIFIER.equals(fields[7])) {
            throw new InvalidDummyValueException();
        }

        final double latitude = Double.parseDouble(fields[1]);
        final double longitude = Double.parseDouble(fields[2]);
        final int elevation = Integer.parseInt(fields[3]);
        final double localizerBearing = Double.parseDouble(fields[6]);
        final String airportCode = fields[8];
        final String runwayNumber = fields[9];
        final String name = concatNameFields(fields);

        return new MarkerEntry(markerType, latitude, longitude, elevation, localizerBearing, airportCode, runwayNumber, name);
    }
}
