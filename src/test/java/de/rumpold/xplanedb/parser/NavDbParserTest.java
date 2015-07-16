package de.rumpold.xplanedb.parser;

import de.rumpold.xplanedb.parser.exceptions.InvalidHeaderException;
import de.rumpold.xplanedb.parser.exceptions.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Adriano on 16.07.2015.
 */
public class NavDbParserTest {
    @Rule
    public ExpectedException rule = ExpectedException.none();

    private NavDbParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new NavDbParser();
    }

    @Test
    public void testParseFileInvalidFile() throws Exception {
        final String fileName = "does.not.exist.dat";

        rule.expect(ParseException.class);
        rule.expectMessage("Cannot open navigation database for reading");
        parser.parseFile(fileName);
    }

    @Test
    public void testParseFileInvalidHeader() throws Exception {
        final String fileName = getClass().getClassLoader().getResource("nav_db_invalid_header.dat").getFile();

        rule.expect(InvalidHeaderException.class);
        parser.parseFile(fileName);
    }
}
