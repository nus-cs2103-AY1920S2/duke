package duke.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.exceptions.DukeException;

public class DateTimeParserTest {

    @Test
    public void testParsingOfDateTimes() throws DukeException {
        DateTimeParser dtp = new DateTimeParser();
        LocalDateTime expectedDate = LocalDate.parse("2000-12-11").atStartOfDay();

        assertThrows(DukeException.class, () -> dtp.parse("11/13/2000")); // invalid date
        assertEquals(expectedDate, dtp.parse("11/12/2000")); // date with slash
        assertEquals(expectedDate, dtp.parse("11-12-2000")); // date with dash
        assertEquals(expectedDate, dtp.parse("11 12 2000")); // date with space
        assertEquals(LocalDateTime.parse("2000-12-11T16:00:00"), dtp.parse("11/12/2000 1600"));
    }
}