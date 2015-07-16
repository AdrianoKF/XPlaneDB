package de.rumpold.xplanedb.model;

/**
 * Created by Adriano on 16.07.2015.
 */
public final class IlsDmeEntry extends DmeEntry {
    private final String airportCode;
    private final String runwayNumber;

    public IlsDmeEntry(double latitude, double longitude, int elevation, int frequency, int range, double bias, String identifier, String airportCode, String runwayNumber, String name) {
        super(latitude, longitude, elevation, frequency, range, bias, identifier, name);
        this.airportCode = airportCode;
        this.runwayNumber = runwayNumber;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getRunwayNumber() {
        return runwayNumber;
    }

    @Override
    public String toString() {
        return "IlsDmeEntry{" +
                "airportCode='" + airportCode + '\'' +
                ", runwayNumber='" + runwayNumber + '\'' +
                "} " + super.toString();
    }
}
