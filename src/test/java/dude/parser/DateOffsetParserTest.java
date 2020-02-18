package dude.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateOffsetParserTest {

    @Test
    void parse_offsetPeriodFormat_correctDateParsed() {
        LocalDate date = new DateOffsetParser().parse("14 days");
        assertEquals(LocalDate.now().plusDays(14), date);
    }
}