package dude.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DayOfWeekParserTest {

    @Test
    void parse_dayOfWeekFormat_correctDateParsed() {
        LocalDate date = new DayOfWeekParser().parse("Monday +3");
        LocalDate threeWeeks = LocalDate.now().plusWeeks(3);
        LocalDate fourWeeks = LocalDate.now().plusWeeks(4);

        assertTrue(date.isAfter(threeWeeks) || date.isEqual(threeWeeks));
        assertTrue(date.isBefore(fourWeeks) || date.isEqual(fourWeeks));
    }
}