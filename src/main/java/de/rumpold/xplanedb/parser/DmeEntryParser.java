package de.rumpold.xplanedb.parser;

import de.rumpold.xplanedb.model.DmeEntry;
import de.rumpold.xplanedb.model.IlsDmeEntry;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import de.rumpold.xplanedb.parser.exceptions.EntryTooShortException;
import de.rumpold.xplanedb.parser.exceptions.ParseException;

/**
 * Created by Adriano on 16.07.2015.
 */
public final class DmeEntryParser extends NavEntryParser {
    @Override
    public NavEntryType[] getAcceptedTypes() {
        return new NavEntryType[]{NavEntryType.INTEGRATED_DME, NavEntryType.STANDALONE_DME};
    }

    @Override
    protected int getFieldCount() {
        return 8;
    }

    @Override
    protected NavEntry parseEntry(String[] fields) throws ParseException, NumberFormatException {
        final double latitude = Double.parseDouble(fields[1]);
        final double longitude = Double.parseDouble(fields[2]);
        final int elevation = Integer.parseInt(fields[3]);
        final int frequency = Integer.parseInt(fields[4]);
        final int range = Integer.parseInt(fields[5]);
        final double bias = Double.parseDouble(fields[6]);
        final String identifier = fields[7];
        final String name;

        if (!identifier.startsWith("I")) {
            name = concatFields(fields, 8);
            return new DmeEntry(latitude, longitude, elevation, frequency, range, bias, identifier, name);
        } else {
            if (fields.length < 11) {
                throw new EntryTooShortException();
            }
            final String airportCode = fields[8];
            final String runwayNumber = fields[9];
            name = concatFields(fields, 10);
            return new IlsDmeEntry(latitude, longitude, elevation, frequency, range, bias, identifier, airportCode, runwayNumber, name);
        }
    }
}
