package duke.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void validStringToDateTest() {
        try {
            Date date = Parser.stringToDate("2019-02-12");
            assertEquals(date.toString(), "Tue Feb 12 00:00:00 GMT+08:00 2019");
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
