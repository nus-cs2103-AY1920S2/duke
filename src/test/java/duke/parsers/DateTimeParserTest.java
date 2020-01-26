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
        String invalidDate = "11/13/2000";
        String validDateWithSlash = "11/12/2000";
        String validDateWithDash = "11-12-2000";
        String validDateWithSpace = "11 12 2000";
        LocalDateTime expectedDate = LocalDate.parse("2000-12-11").atStartOfDay();
        String validDateTime = "11/12/2000 1600";
        LocalDateTime expectedDateTime = LocalDateTime.parse("2000-12-11T16:00:00");
        
        assertThrows(DukeException.class, () -> dtp.parse(invalidDate));
        assertEquals(expectedDate, dtp.parse(validDateWithSlash));
        assertEquals(expectedDate, dtp.parse(validDateWithDash));
        assertEquals(expectedDate, dtp.parse(validDateWithSpace));
        assertEquals(expectedDateTime, dtp.parse(validDateTime));
    }
}