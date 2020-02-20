package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for {@code Deadline}.
 */
public class DeadlineTest {
    /**
     * Tests the inheritance of {@code Deadline} from its superclass {@code Task}.
     */
    @Test
    public void testInheritance() {
        Deadline testDeadline = new Deadline("Event pizza!", LocalDateTime.now());
        assertEquals("Event pizza!", testDeadline.getDescription());
        assertFalse(testDeadline.isCompleted());
        testDeadline.completeTask();
        assertTrue(testDeadline.isCompleted());
        assertTrue(new Deadline("Already completed", LocalDateTime.now(), true).isCompleted());
    }

    /**
     * Tests the overridden {@code toString()} method.
     */
    @Test
    public void testToString() {
        String resultOne = new Deadline("Deadline!!", LocalDateTime.of(1900, 12, 30, 18, 0)).toString();
        String resultTwo = new Deadline("brunch", LocalDate.now().atTime(17, 0), true).toString();
        Logger logger = Logger.getLogger("Deadline Test");
        logger.log(Level.WARNING, resultOne);
        logger.log(Level.WARNING, resultTwo);
        assertEquals("[D][\u2718] Deadline!! (by: Dec 30 1900 6:00 pm [OVERDUE])", resultOne); // cross mark
        assertEquals("[D][\u2713] brunch (by: Today 5:00 pm)", resultTwo); // tick mark
    }
}
