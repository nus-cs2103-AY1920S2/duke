package duke;

import duke.parser.StringParser;
import duke.parser.exception.ParseException;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestStringParser {
    @Test
    public void testParseDate1() throws DukeException {
        LocalDate actualDate = StringParser.parseDate("2020-12-25");
        LocalDate expectedDate = LocalDate.of(2020, 12, 25);

        assertEquals(actualDate, expectedDate);
    }

    @Test
    public void testParseDate2() {
        assertThrows(DukeException.class, () -> StringParser.parseDate("potato"));
    }

    @Test
    public void testParseInt1() throws ParseException {
        int actualInt = StringParser.parseInt("3");
        int expectedInt = 3;

        assertEquals(actualInt, expectedInt);
    }
}
