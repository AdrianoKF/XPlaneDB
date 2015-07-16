package de.rumpold.xplanedb.parser;

import de.rumpold.xplanedb.model.GlideslopeEntry;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.VorEntry;
import de.rumpold.xplanedb.parser.exceptions.EntryTooShortException;
import de.rumpold.xplanedb.parser.exceptions.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Adriano on 16.07.2015.
 */
public class VorEntryParserTest {
    @Rule public ExpectedException rule = ExpectedException.none();
    private VorEntryParser parser;

    @Before
    public void setUp() {
        parser = new VorEntryParser();
    }

    @Test
    public void testGetAcceptedTypes() throws Exception {
        assertArrayEquals(parser.getAcceptedTypes(), new NavEntry.NavEntryType[]{NavEntry.NavEntryType.VOR});
    }

    @Test
    public void testGetFieldCount() throws Exception {
        assertEquals(9, parser.getFieldCount());
    }

    @Test
    public void testParseEntryValid() throws Exception {
        final String line = "3 47.43538889 -122.30961111 354 11680 130 19.0 SEA SEATTLE VORTAC";
        final VorEntry entry = (VorEntry) parser.parseLine(line);

        assertEquals(47.43538889, entry.getLatitude(), 1e-6);
        assertEquals(-122.30961111, entry.getLongitude(), 1e-6);
        assertEquals(354, entry.getElevation());
        assertEquals(11680, entry.getFrequency());
        assertEquals(130, entry.getRange());
        assertEquals(19.0, entry.getVariation(), 1e-6);
        assertEquals("SEA", entry.getIdentifier());
        assertEquals("SEATTLE VORTAC", entry.getName());
    }

    @Test
    public void testParseEntryInvalidTooShort() throws Exception {
        final String invalidLine = "3 47.43538889 -122.30961111 354 11680 19.0 SEA SEATTLE VORTAC";

        rule.expect(ParseException.class);
        final VorEntry entry = (VorEntry) parser.parseLine(invalidLine);
    }
}
