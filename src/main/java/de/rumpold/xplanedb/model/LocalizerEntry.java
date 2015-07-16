package de.rumpold.xplanedb.model;

/**
 * Created by Adriano on 14.07.2015.
 */
public final class LocalizerEntry extends NavEntry {
    public enum LocalizerType {
        ILS, STANDALONE
    }

    private final LocalizerType type;

    private final int frequency;
    private final int range;
    private final double bearing;
    private final String identifier;
    private final String airportCode;
    private final String runwayNumber;

    public LocalizerEntry(LocalizerType type, double latitude, double longitude, int elevation, int frequency, int range, double bearing, String identifier, String airportCode, String runwayNumber, String name) {
        super(latitude, longitude, elevation, name);

        this.type = type;
        this.frequency = frequency;
        this.range = range;
        this.bearing = bearing;
        this.identifier = identifier;
        this.airportCode = airportCode;
        this.runwayNumber = runwayNumber;
    }

    public LocalizerType getType() {
        return type;
    }

    public String getRunwayNumber() {
        return runwayNumber;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getRange() {
        return range;
    }

    public double getBearing() {
        return bearing;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getAirportCode() {
        return airportCode;
    }

    @Override
    public String toString() {
        return "LocalizerEntry{" +
                "frequency=" + frequency +
                ", range=" + range +
                ", bearing=" + bearing +
                ", identifier='" + identifier + '\'' +
                ", airportCode='" + airportCode + '\'' +
                ", runwayNumber='" + runwayNumber + '\'' +
                "} " + super.toString();
    }
}
