package duke;

import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestParser {
    @Test
    public void testParseDate1() throws DukeException {
        LocalDate actualDate = Parser.parseDate("2020-12-25");
        LocalDate expectedDate = LocalDate.of(2020, 12, 25);

        assertEquals(actualDate, expectedDate);
    }

    @Test
    public void testParseDate2() throws DukeException {
        assertThrows(DukeException.class, () -> Parser.parseDate("potato"));
    }

    @Test
    public void testParseInt1() throws DukeException {
        int actualInt = Parser.parseInt("3");
        int expectedInt = 3;

        assertEquals(actualInt, expectedInt);
    }
}
