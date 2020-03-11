package dude.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FullDateParserTest {
    private FullDateParser parser = new FullDateParser("yyyy-MM-dd", new String[0], "");

    @Test
    void parse_isoFormat_correctDateParsed() {
        LocalDate date = parser.parse("2103-01-01");
        assertEquals(LocalDate.of(2103, 1, 1), date);
    }

    @Test
    void parse_wrongIsoFormat_null() {
        LocalDate date = parser.parse("2103/02/02");
        assertNull(date);
    }
}