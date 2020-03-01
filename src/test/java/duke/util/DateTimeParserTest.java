package duke.util;

import static duke.util.MagicStrings.ERROR_WRONG_DATE_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;

import duke.exception.DuchessException;

/**
 * JUnit test class for {@code DateTimeParser}.
 */
public class DateTimeParserTest {
    /**
     * Tests the parsing of a valid date time string.
     */
    @Test
    public void parse_validDateTimeString_success() {
        assertEquals(LocalDate.now().atTime(17, 0).toString(), DateTimeParser.parseDateTime("today").toString());
        assertEquals(LocalDate.now().atTime(21, 0).toString(), DateTimeParser.parseDateTime("tonight").toString());
        assertEquals(LocalDate.now().plusDays(1).atTime(17, 0).toString(),
                DateTimeParser.parseDateTime("tmr").toString());
        assertEquals(LocalDate.now().plusDays(1).atTime(17, 0).toString(),
                DateTimeParser.parseDateTime("tomorrow").toString());

        // Days of the week
        assertEquals(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(17, 0).toString(),
                DateTimeParser.parseDateTime("monday").toString());
        assertEquals(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY)).atTime(17, 0).toString(),
                DateTimeParser.parseDateTime("tuesday").toString());
        assertEquals(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)).atTime(17, 0).toString(),
                DateTimeParser.parseDateTime("wednesday").toString());
        assertEquals(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY)).atTime(17, 0).toString(),
                DateTimeParser.parseDateTime("thursday").toString());
        assertEquals(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).atTime(17, 0).toString(),
                DateTimeParser.parseDateTime("friday").toString());
        assertEquals(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).atTime(17, 0).toString(),
                DateTimeParser.parseDateTime("saturday").toString());
        assertEquals(LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).atTime(17, 0).toString(),
                DateTimeParser.parseDateTime("sunday").toString());
    }

    /**
     * Tests the error handling of the parser for invalid date time strings.
     */
    @Test
    public void parse_invalidDateTimeString_throwsException() {
        try {
            DateTimeParser.parseDateTime("ho ha ho ha whatever day");
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_WRONG_DATE_FORMAT, e.getMessage());
        }
    }
}
