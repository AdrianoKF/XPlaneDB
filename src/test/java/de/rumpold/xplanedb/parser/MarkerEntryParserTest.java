package de.rumpold.xplanedb.parser;

import de.rumpold.xplanedb.model.MarkerEntry;
import de.rumpold.xplanedb.model.MarkerEntry.MarkerType;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NavEntry.NavEntryType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Adriano on 16.07.2015.
 */
public class MarkerEntryParserTest {
    @Rule public ExpectedException rule = ExpectedException.none();
    private MarkerEntryParser parser;

    @Before
    public void setUp() {
        parser = new MarkerEntryParser();
    }

    @Test
    public void testGetAcceptedTypes() throws Exception {
        assertArrayEquals(parser.getAcceptedTypes(), new NavEntryType[]{NavEntryType.ILS_OM, NavEntryType.ILS_MM, NavEntryType.ILS_IM});
    }

    @Test
    public void testGetFieldCount() throws Exception {
        assertEquals(11, parser.getFieldCount());
    }

    @Test
    public void testParseLineOuter() throws Exception {
        final String line = "7  29.47034400 -098.38726700    809     0   0     311.755 ---- KSAT 30L OM";
        final MarkerEntry entry = (MarkerEntry) parser.parseLine(line);

        assertEquals(MarkerType.OUTER, entry.getMarkerType());
        assertEquals(29.470344, entry.getLatitude(), 1e-6);
        assertEquals(-98.387267, entry.getLongitude(), 1e-6);
        assertEquals(809, entry.getElevation());
        assertEquals(311.755, entry.getLocalizerBearing(), 1e-6);
        assertEquals("KSAT", entry.getAirportCode());
        assertEquals("30L", entry.getRunwayNumber());
        assertEquals("OM", entry.getName());
    }

    @Test
    public void testParseLineMiddle() throws Exception {
        final String line = "8 47.47223300 -122.31102500 433 0 0 180.343 ---- KSEA 16L MM";
        final MarkerEntry entry = (MarkerEntry) parser.parseLine(line);

        assertEquals(MarkerType.MIDDLE, entry.getMarkerType());
        assertEquals(47.472233, entry.getLatitude(), 1e-6);
        assertEquals(-122.311025, entry.getLongitude(), 1e-6);
        assertEquals(433, entry.getElevation());
        assertEquals(180.343, entry.getLocalizerBearing(), 1e-6);
        assertEquals("KSEA", entry.getAirportCode());
        assertEquals("16L", entry.getRunwayNumber());
        assertEquals("MM", entry.getName());
    }

    @Test
    public void testParseLineInner() throws Exception {
        final String line = "9  47.46617200 -122.31783880    433     0   0     180.336 ---- KSEA 16R IM";
        final MarkerEntry entry = (MarkerEntry) parser.parseLine(line);

        assertEquals(MarkerType.INNER, entry.getMarkerType());
        assertEquals(47.466172, entry.getLatitude(), 1e-6);
        assertEquals(-122.3178388, entry.getLongitude(), 1e-6);
        assertEquals(433, entry.getElevation());
        assertEquals(180.336, entry.getLocalizerBearing(), 1e-6);
        assertEquals("KSEA", entry.getAirportCode());
        assertEquals("16R", entry.getRunwayNumber());
        assertEquals("IM", entry.getName());
    }
}
