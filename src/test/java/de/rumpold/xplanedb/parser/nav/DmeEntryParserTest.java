package de.rumpold.xplanedb.parser.nav;

import de.rumpold.xplanedb.model.DmeEntry;
import de.rumpold.xplanedb.model.IlsDmeEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Adriano on 16.07.2015.
 */
public class DmeEntryParserTest {
    @Rule public ExpectedException rule = ExpectedException.none();
    private DmeEntryParser parser;

    @Before
    public void setUp() {
        parser = new DmeEntryParser();
    }

    @Test
    public void testGetAcceptedTypes() throws Exception {
        assertArrayEquals(parser.getAcceptedTypes(), new NavEntryType[]{NavEntryType.INTEGRATED_DME, NavEntryType.STANDALONE_DME});
    }

    @Test
    public void testGetFieldCount() throws Exception {
        assertEquals(8, parser.getFieldCount());
    }

    @Test
    public void testParseLineIls() throws Exception {
        final String line = "12 47.43433300 -122.30630000 369 11030 18 0.000 ISNQ KSEA 16L DME-ILS";
        final IlsDmeEntry entry = (IlsDmeEntry) parser.parseLine(line);

        assertEquals(47.434333, entry.getLatitude(), 1e-6);
        assertEquals(-122.3063, entry.getLongitude(), 1e-6);
        assertEquals(369, entry.getElevation());
        assertEquals(11030, entry.getFrequency());
        assertEquals(18, entry.getRange());
        assertEquals(0.0, entry.getBias(), 1e-6);
        assertEquals("ISNQ", entry.getIdentifier());
        assertEquals("KSEA", entry.getAirportCode());
        assertEquals("16L", entry.getRunwayNumber());
        assertEquals("DME-ILS", entry.getName());
    }

    @Test
    public void testParseLineColocated() throws Exception {
        final String line = "12 47.43538889 -122.30961111 354 11680 130 0.0 SEA SEATTLE VORTAC DME";
        final DmeEntry entry = (DmeEntry) parser.parseLine(line);

        assertTrue(entry.getClass() == DmeEntry.class);
        assertEquals(47.43538889, entry.getLatitude(), 1e-6);
        assertEquals(-122.30961111, entry.getLongitude(), 1e-6);
        assertEquals(354, entry.getElevation());
        assertEquals(11680, entry.getFrequency());
        assertEquals(130, entry.getRange());
        assertEquals(0.0, entry.getBias(), 1e-6);
        assertEquals("SEA", entry.getIdentifier());
        assertEquals("SEATTLE VORTAC DME", entry.getName());
    }

    @Test
    public void testParseLineStandalone() throws Exception {
        final String line = "13  34.73255000 -120.58305600    370 11225  40    0.0 VBG  VANDENBERG TACAN";
        final DmeEntry entry = (DmeEntry) parser.parseLine(line);

        assertTrue(entry.getClass() == DmeEntry.class);
        assertEquals(34.73255, entry.getLatitude(), 1e-6);
        assertEquals(-120.583056, entry.getLongitude(), 1e-6);
        assertEquals(370, entry.getElevation());
        assertEquals(11225, entry.getFrequency());
        assertEquals(40, entry.getRange());
        assertEquals(0.0, entry.getBias(), 1e-6);
        assertEquals("VBG", entry.getIdentifier());
        assertEquals("VANDENBERG TACAN", entry.getName());
    }
}
