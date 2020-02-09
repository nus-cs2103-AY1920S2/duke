package dude.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateParserTest {

    @Test
    void parse_isoFormat_correctDateParsed() throws ParsingException {
        LocalDate date = DateParser.parse("2103-01-01", "event");
        assertEquals(LocalDate.of(2103, 1, 1), date);
    }

    @Test
    void parse_wrongIsoFormat_exceptionThrown() {
        try {
            DateParser.parse("2103/02/02", "event");
            fail();
        } catch (ParsingException e) {
            assertEquals(Parser.USAGES.get("event"), e.getUsageMsg());
        }
    }

    @Test
    void parse_dMmmYyyyFormat_correctDateParsed() throws ParsingException {
        LocalDate date = DateParser.parse("1 Jan 2103", "deadline");
        assertEquals(LocalDate.of(2103, 1, 1), date);
    }

    @Test
    void parse_offsetPeriodFormat_correctDateParsed() throws ParsingException {
        LocalDate date = DateParser.parse("14 days", "deadline");
        assertEquals(LocalDate.now().plusDays(14), date);
    }

    @Test
    void parse_dayOfWeekFormat_correctDateParsed() throws ParsingException {
        LocalDate date = DateParser.parse("Monday +3", "check");
        LocalDate threeWeeks = LocalDate.now().plusWeeks(3);
        LocalDate fourWeeks = LocalDate.now().plusWeeks(4);

        assertTrue(date.isAfter(threeWeeks) || date.isEqual(threeWeeks));
        assertTrue(date.isBefore(fourWeeks) || date.isEqual(fourWeeks));
    }
}