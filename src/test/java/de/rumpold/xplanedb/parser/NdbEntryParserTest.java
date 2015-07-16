package de.rumpold.xplanedb.parser;

import de.rumpold.xplanedb.model.GlideslopeEntry;
import de.rumpold.xplanedb.model.NavEntry;
import de.rumpold.xplanedb.model.NdbEntry;
import de.rumpold.xplanedb.parser.exceptions.EntryTooShortException;
import de.rumpold.xplanedb.parser.exceptions.InvalidDummyValueException;
import de.rumpold.xplanedb.parser.exceptions.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Adriano on 16.07.2015.
 */
public class NdbEntryParserTest {
    @Rule public ExpectedException rule = ExpectedException.none();
    private NdbEntryParser parser;

    @Before
    public void setUp() {
        parser = new NdbEntryParser();
    }

    @Test
    public void testGetAcceptedTypes() throws Exception {
        assertArrayEquals(parser.getAcceptedTypes(), new NavEntry.NavEntryType[]{NavEntry.NavEntryType.NDB});
    }

    @Test
    public void testGetFieldCount() throws Exception {
        assertEquals(9, parser.getFieldCount());
    }

    @Test
    public void testParseEntryValid() throws Exception {
        final String line = "2 47.63252778 -122.38952778 0 362 50 0.0 BF NOLLA NDB";
        final NdbEntry entry = (NdbEntry) parser.parseLine(line);

        assertEquals(47.63252778, entry.getLatitude(), 1e-6);
        assertEquals(-122.38952778, entry.getLongitude(), 1e-6);
        assertEquals(0, entry.getElevation());
        assertEquals(362, entry.getFrequency());
        assertEquals(50, entry.getRange());
        assertEquals("BF", entry.getIdentifier());
        assertEquals("NOLLA NDB", entry.getName());
    }

    @Test
    public void testParseEntryInvalidDummyValue() throws Exception {
        final String invalidLine = "2 47.63252778 -122.38952778 0 362 50 1.0 BF NOLLA NDB";

        rule.expect(InvalidDummyValueException.class);
        final GlideslopeEntry entry = (GlideslopeEntry) parser.parseLine(invalidLine);
    }

    @Test
    public void testParseEntryInvalidTooShort() throws Exception {
        final String invalidLine = "2 47.63252778 -122.38952778 0   50 0.0 BF NOLLA NDB";

        rule.expect(ParseException.class);
        final NdbEntry entry = (NdbEntry) parser.parseLine(invalidLine);
    }
}
