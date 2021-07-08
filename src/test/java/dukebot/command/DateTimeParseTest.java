package dukebot.command;

import dukebot.util.DateTimeParse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeParseTest {

    @Test
    void parseDate() {
        LocalDateTime testTime = LocalDateTime.of(2020,1,2,0,0);
        LocalDateTime now = LocalDateTime.now();

        // yyyy/M/d
        assertEquals(testTime, DateTimeParse.parseDate("2020-01-2"));
        // d/M/yyyy
        assertEquals(testTime, DateTimeParse.parseDate("02/1/2020"));
    }
}