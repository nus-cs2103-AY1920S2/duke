package app.util;

import org.junit.jupiter.api.Test;
import app.exceptions.InvalidDateTimeFormatException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {
    @Test
    public void createDate_correctDefaultFormat() throws InvalidDateTimeFormatException {
        Date output = Date.from("2019-01-01 1800");
        assertEquals("Jan 1 2019 06:00PM", output.toString());
    }

    @Test
    public void createDate_wrongDefaultFormat_exceptionThrown() {
        try {
            Date.from("2019-01-01");
        } catch (InvalidDateTimeFormatException e) {
            assertEquals("Ah! I can only understand this format: yyyy-MM-dd HHmm", e.getMessage());
        }
    }

    @Test
    public void createDate_correctAlternateFormat() throws InvalidDateTimeFormatException {
        Date output = Date.fromFormat("01-01-2019 0000", "dd-MM-yyyy HHmm");
        assertEquals("Jan 1 2019 12:00AM", output.toString());
    }

    @Test
    public void createDate_wrongAlternateFormat_exceptionThrown() {
        try {
            Date.fromFormat("2019-01-1", "dd-MM-yyyy HHmm");
        } catch (InvalidDateTimeFormatException e) {
            assertEquals("Ah! I can only understand this format: dd-MM-yyyy HHmm", e.getMessage());
        }
    }

    @Test
    public void outputDate_withFormat() throws InvalidDateTimeFormatException {
        Date output = Date.from("2019-01-01 1800")
            .withFormat("dd-MM-yyyy");
        assertEquals("01-01-2019", output.toString());
    }
}