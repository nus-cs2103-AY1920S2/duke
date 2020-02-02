package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void testDeadline() {
        DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter inputTimeFormat = DateTimeFormatter.ofPattern("HHmm");
        Task deadline = new Deadline("Deadline", LocalDate.parse("2020-02-28", inputDateFormat),
                LocalTime.parse("1800", inputTimeFormat));
        String expectedText = "[D][\u2718] Deadline (by: Feb 28 2020, 6:00pm)";
        String actualText = deadline.toString();
        assertEquals(expectedText, actualText);
    }
}
