package de.rumpold.xplanedb.model;

/**
 * Created by Adriano on 14.07.2015.
 */
public final class GlideslopeEntry extends NavEntry {
    private final int frequency;
    private final int range;
    private final double localizerBearing;
    private final String identifier;
    private final String airportCode;
    private final String runwayNumber;

    public GlideslopeEntry(double latitude, double longitude, int elevation, int frequency, int range, double localizerBearing, String identifier, String airportCode, String runwayNumber, String name) {
        super(latitude, longitude, elevation, name);

        this.frequency = frequency;
        this.range = range;
        this.localizerBearing = localizerBearing;
        this.identifier = identifier;
        this.airportCode = airportCode;
        this.runwayNumber = runwayNumber;
    }

    @Override
    public String toString() {
        return "GlideslopeEntry{" +
                "frequency=" + frequency +
                ", range=" + range +
                ", localizerBearing=" + localizerBearing +
                ", identifier='" + identifier + '\'' +
                ", airportCode='" + airportCode + '\'' +
                ", runwayNumber='" + runwayNumber + '\'' +
                "} " + super.toString();
    }
}
