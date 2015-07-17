package de.rumpold.xplanedb.parser.nav;

import de.rumpold.xplanedb.model.GlideslopeEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import de.rumpold.xplanedb.parser.exceptions.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Adriano on 16.07.2015.
 */
public class GlideslopeEntryParserTest {
    @Rule public ExpectedException rule = ExpectedException.none();
    private GlideslopeEntryParser parser;

    @Before
    public void setUp() {
        parser = new GlideslopeEntryParser();
    }

    @Test
    public void testGetAcceptedTypes() throws Exception {
        assertArrayEquals(parser.getAcceptedTypes(), new NavEntryType[]{NavEntryType.GLIDESLOPE});
    }

    @Test
    public void testGetFieldCount() throws Exception {
        assertEquals(11, parser.getFieldCount());
    }

    @Test
    public void testParseEntryValid() throws Exception {
        final String line = "6 47.46081700 -122.30939400 425 11030 10 300180.343 ISNQ KSEA 16L GS";
        final GlideslopeEntry entry = (GlideslopeEntry) parser.parseLine(line);

        assertEquals(47.460817, entry.getLatitude(), 1e-6);
        assertEquals(-122.309394, entry.getLongitude(), 1e-6);
        assertEquals(425, entry.getElevation());
        assertEquals(11030, entry.getFrequency());
        assertEquals(10, entry.getRange());
        assertEquals(300180.343, entry.getLocalizerBearing(), 1e-6);
        assertEquals("ISNQ", entry.getIdentifier());
        assertEquals("KSEA", entry.getAirportCode());
        assertEquals("16L", entry.getRunwayNumber());
        assertEquals("GS", entry.getName());
    }

    @Test
    public void testParseEntryInvalidTooShort() throws Exception {
        final String invalidLine = "6  40.75644400  016.94085000   1184        10  300321.163  LIBV 32L GS";

        rule.expect(ParseException.class);
        final GlideslopeEntry entry = (GlideslopeEntry) parser.parseLine(invalidLine);
    }
}
