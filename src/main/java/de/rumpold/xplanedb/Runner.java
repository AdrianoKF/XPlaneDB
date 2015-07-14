package de.rumpold.xplanedb;

import de.rumpold.xplanedb.exceptions.ParseException;
import de.rumpold.xplanedb.parser.NavDbParser;

/**
 * Created by Adriano on 14.07.2015.
 */
public class Runner {
    public static void main(String args[]) {
        final NavDbParser parser = new NavDbParser();
        try {
            parser.parseFile("c:/Games/X-Plane 10/Resources/default data/earth_nav.dat");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
