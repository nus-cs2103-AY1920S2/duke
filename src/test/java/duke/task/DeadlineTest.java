package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][\u2718] Deedoo (by: Feb 10 2019 20:35)", new Deadline("Deedoo", LocalDate.of(2019, 02, 10), LocalTime.of(20, 35)).toString());
    }
}