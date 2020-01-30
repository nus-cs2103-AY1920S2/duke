package duke.other;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateValidatorTest {

    @Test
    void isValidDate() {
        DateValidator dv = new DateValidator(DateTimeFormatter.ofPattern("yyyy/M/d"));
        assertEquals(true, dv.isValidDate("2020/01/30"));
    }

    @Test
    void isValidTime() {
        DateValidator dv = new DateValidator(DateTimeFormatter.ofPattern("HH:mm"));
        assertEquals(true, dv.isValidTime("18:30"));

    }
}