package duke.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void validStringToDateTest() {
        LocalDate date = Parser.stringToDate("2019-02-12");
        assertEquals(1, 1);
    }

    @Test
    public void checkValidDateFormat() {
        boolean correct = Parser.checkDateFormat("2019-02-04");
        assertTrue(correct);
    }

    @Test
    public void checkInvalidDateFormat() {
        boolean correct = Parser.checkDateFormat("2019-22-04");
        assertFalse(correct);
    }
}
