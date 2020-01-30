package duchess.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    public void testInheritance() {
        Deadline testDeadline = new Deadline("Event pizza!", LocalDateTime.now());
        assertEquals("Event pizza!", testDeadline.getDescription());
        assertFalse(testDeadline.isCompleted());
        testDeadline.toggleIsCompleted();
        assertTrue(testDeadline.isCompleted());
        assertTrue(new Deadline("Already completed", LocalDateTime.now(), true).isCompleted());
    }

    @Test
    public void testToString() {
        assertEquals("[D][\u2718] Deadline!! (by: Dec 30 1900 6:00 pm [OVERDUE])",
                new Deadline("Deadline!!", LocalDateTime.of(1900, 12, 30, 18, 0)).toString());
        assertEquals("[D][\u2713] brunch (by: Today 5:00 pm)",
                new Deadline("brunch", LocalDate.now().atTime(17, 0), true).toString());
    }
}
