package de.rumpold.xplanedb.model;

/**
 * Created by Adriano on 16.07.2015.
 */
public class DmeEntry extends NavEntry {
    private final int frequency;
    private final int range;
    private final double bias;
    private final String identifier;

    public DmeEntry(double latitude, double longitude, int elevation, int frequency, int range, double bias, String identifier, String name) {
        super(latitude, longitude, elevation, name);
        this.frequency = frequency;
        this.range = range;
        this.bias = bias;
        this.identifier = identifier;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getRange() {
        return range;
    }

    public double getBias() {
        return bias;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "DmeEntry{" +
                "frequency=" + frequency +
                ", range=" + range +
                ", bias=" + bias +
                ", identifier='" + identifier + '\'' +
                "} " + super.toString();
    }
}
