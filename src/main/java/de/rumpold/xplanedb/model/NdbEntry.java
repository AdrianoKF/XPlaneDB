package de.rumpold.xplanedb.model;

/**
 * Created by Adriano on 14.07.2015.
 */
public final class NdbEntry extends NavEntry {
    private final int frequency;
    private final int range;
    private final String identifier;

    public NdbEntry(double latitude, double longitude, int elevation, int frequency, int range, String identifier, String name) {
        super(latitude, longitude, elevation, name);
        this.frequency = frequency;
        this.range = range;
        this.identifier = identifier;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getRange() {
        return range;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return "NdbEntry{" +
                "frequency=" + frequency +
                ", range=" + range +
                ", identifier='" + identifier + '\'' +
                "} " + super.toString();
    }
}
