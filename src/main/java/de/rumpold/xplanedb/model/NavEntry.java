package de.rumpold.xplanedb.model;

import java.util.HashMap;

/**
 * Created by Adriano on 14.07.2015.
 */
public abstract class NavEntry {
    public enum NavEntryType {
        NDB(2),
        VOR(3),
        ILS_LOC(4),
        LOC(5),
        GLIDESLOPE(6),
        ILS_OM(7),
        ILS_MM(8),
        ILS_IM(9),
        INTEGRATED_DME(12),
        STANDALONE_DME(13);

        private final int value;
        private static HashMap<Integer, NavEntryType> inverseMapping = new HashMap<>();

        static {
            for (NavEntryType navEntryType : NavEntryType.values()) {
                inverseMapping.put(navEntryType.getValue(), navEntryType);
            }
        }

        NavEntryType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static NavEntryType fromValue(int value) {
            return inverseMapping.get(value);
        }
    }

    protected final double latitude;
    protected final double longitude;
    protected final int elevation;
    protected final String name;

    public NavEntry(double latitude, double longitude, int elevation, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getElevation() {
        return elevation;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "NavEntry{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", elevation=" + elevation +
                ", name='" + name + '\'' +
                '}';
    }
}
