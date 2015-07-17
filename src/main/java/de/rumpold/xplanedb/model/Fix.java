package de.rumpold.xplanedb.model;

/**
 * Created by Adriano on 17.07.2015.
 */
public class Fix {
    private final double latitude;
    private final double longitude;
    private final String name;

    public Fix(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Fix{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                '}';
    }
}
