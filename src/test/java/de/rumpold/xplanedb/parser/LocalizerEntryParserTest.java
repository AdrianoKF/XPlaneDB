package de.rumpold.xplanedb.parser;

import de.rumpold.xplanedb.model.LocalizerEntry;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import de.rumpold.xplanedb.model.VorEntry;
import de.rumpold.xplanedb.parser.exceptions.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Adriano on 16.07.2015.
 */
public class LocalizerEntryParserTest {
    @Rule public ExpectedException rule = ExpectedException.none();
    private LocalizerEntryParser parser;

    @Before
    public void setUp() {
        parser = new LocalizerEntryParser();
    }

    @Test
    public void testGetAcceptedTypes() throws Exception {
        assertArrayEquals(parser.getAcceptedTypes(), new NavEntryType[]{NavEntryType.ILS_LOC, NavEntryType.LOC});
    }

    @Test
    public void testGetFieldCount() throws Exception {
        assertEquals(11, parser.getFieldCount());
    }

    @Test
    public void testParseEntryValid() throws Exception {
        final String line = "4 47.42939200 -122.30805600 338 11030 18 180.343 ISNQ KSEA 16L ILS-cat-I";
        final LocalizerEntry entry = (LocalizerEntry) parser.parseLine(line);

        assertEquals(47.42939200, entry.getLatitude(), 1e-6);
        assertEquals(-122.30805600, entry.getLongitude(), 1e-6);
        assertEquals(338, entry.getElevation());
        assertEquals(11030, entry.getFrequency());
        assertEquals(18, entry.getRange());
        assertEquals(180.343, entry.getBearing(), 1e-6);
        assertEquals("ISNQ", entry.getIdentifier());
        assertEquals("KSEA", entry.getAirportCode());
        assertEquals("16L", entry.getRunwayNumber());
        assertEquals("ILS-cat-I", entry.getName());
    }

    @Test
    public void testParseEntryInvalidTooShort() throws Exception {
        final String invalidLine = "4 47.42939200 -122.30805600 338 11030  180.343 ISNQ KSEA 16L ILS-cat-I";

        rule.expect(ParseException.class);
        final LocalizerEntry entry = (LocalizerEntry) parser.parseLine(invalidLine);
    }
}
