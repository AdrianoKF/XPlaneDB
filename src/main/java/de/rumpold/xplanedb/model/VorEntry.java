package de.rumpold.xplanedb.model;

/**
 * Created by Adriano on 14.07.2015.
 */
public final class VorEntry extends NavEntry {
    private final int frequency;
    private final int range;
    private final double variation;
    private String identifier;

    public VorEntry(double latitude, double longitude, int elevation, int frequency, int range, double variation, String identifier, String name) {
        super(latitude, longitude, elevation, name);
        this.frequency = frequency;
        this.range = range;
        this.variation = variation;
        this.identifier = identifier;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getRange() {
        return range;
    }

    public double getVariation() {
        return variation;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "VorEntry{" +
                "frequency=" + frequency +
                ", range=" + range +
                ", variation=" + variation +
                ", identifier='" + identifier + '\'' +
                "} " + super.toString();
    }
}
