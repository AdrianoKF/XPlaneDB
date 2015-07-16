package de.rumpold.xplanedb.model;

/**
 * Created by Adriano on 16.07.2015.
 */
public final class MarkerEntry extends NavEntry {
    public enum MarkerType {
        OUTER, INNER, MIDDLE
    }

    private final MarkerType markerType;

    private final double localizerBearing;
    private final String airportCode;
    private final String runwayNumber;

    public MarkerType getMarkerType() {
        return markerType;
    }

    public double getLocalizerBearing() {
        return localizerBearing;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getRunwayNumber() {
        return runwayNumber;
    }

    public MarkerEntry(MarkerType markerType, double latitude, double longitude, int elevation, double localizerBearing, String airportCode, String runwayNumber, String name) {
        super(latitude, longitude, elevation, name);
        this.markerType = markerType;
        this.localizerBearing = localizerBearing;
        this.airportCode = airportCode;
        this.runwayNumber = runwayNumber;
    }
}
