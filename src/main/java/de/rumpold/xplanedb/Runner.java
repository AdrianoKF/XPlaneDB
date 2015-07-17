package de.rumpold.xplanedb;

import de.rumpold.xplanedb.model.Fix;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.parser.exceptions.ParseException;
import de.rumpold.xplanedb.parser.fix.FixDbParser;
import de.rumpold.xplanedb.parser.nav.NavDbParser;

import java.util.List;

/**
 * Created by Adriano on 14.07.2015.
 */
public class Runner {
    public static void main(String args[]) {
        final NavDbParser navDbParser = new NavDbParser(true);
        final FixDbParser fixDbParser = new FixDbParser();
        try {
            List<NavEntry> navDb = navDbParser.parseFile("c:/Games/X-Plane 10/Resources/default data/earth_nav.dat");
            List<Fix> fixDb = fixDbParser.parseFile("c:/Games/X-Plane 10/Resources/default data/earth_fix.dat");

            navDb.stream().limit(25).forEach(e -> System.out.println(e));
            fixDb.stream().limit(25).forEach(f -> System.out.println(f));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
