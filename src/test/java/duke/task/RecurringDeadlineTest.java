package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.junit.jupiter.api.Test;

import duke.util.Frequency;

/**
 * JUnit test class for {@code RecurringDeadline}.
 */
public class RecurringDeadlineTest {
    /**
     * Tests the inheritance of {@code RecurringDeadline} from its superclass {@code Deadline}.
     */
    @Test
    public void testInheritance_noRepeatEndTime_success() {
        RecurringDeadline testRecurringDeadline = new RecurringDeadline("Go for a run!",
                LocalDate.now().atTime(18, 0), Frequency.DAILY);
        assertEquals("Go for a run!", testRecurringDeadline.getDescription());
        assertFalse(testRecurringDeadline.isCompleted());
        assertEquals(LocalDate.now().atTime(18, 0).toString(), testRecurringDeadline.deadline.toString());
    }

    /**
     * Tests the inheritance of {@code RecurringDeadline} from its superclass {@code Deadline}.
     */
    @Test
    public void testInheritance_hasRepeatEndTime_success() {
        RecurringDeadline testRecurringDeadline = new RecurringDeadline("Go for a run!",
                LocalDate.now().atTime(18, 0).plusDays(1), Frequency.WEEKLY,
                LocalDateTime.now().plusWeeks(2));
        assertEquals("Go for a run!", testRecurringDeadline.getDescription());
        assertFalse(testRecurringDeadline.isCompleted());
        testRecurringDeadline.completeTask();
        assertFalse(testRecurringDeadline.isCompleted());
        testRecurringDeadline.completeTask();
        assertTrue(testRecurringDeadline.isCompleted());
        assertEquals(LocalDate.now().atTime(18, 0).plusDays(1).plusWeeks(1).toString(),
                testRecurringDeadline.deadline.toString());
    }

    /**
     * Tests the overridden {@code snooze()} method.
     */
    @Test
    public void testSnooze() {
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endRepeatTime = startTime.plusWeeks(5);
        RecurringDeadline testRecurringDeadline = new RecurringDeadline(
                "Snooze this!", startTime, Frequency.WEEKLY, endRepeatTime);
        assertEquals(endRepeatTime.toString(), testRecurringDeadline.getRepeatEndTime().toString());
        testRecurringDeadline.snooze(Period.ofWeeks(3));
        endRepeatTime = endRepeatTime.plus(Period.ofWeeks(3));
        assertEquals(endRepeatTime.toString(), testRecurringDeadline.getRepeatEndTime().toString());
    }

    /**
     * Tests the overridden {@code toString()} method.
     */
    @Test
    public void testToString() {
        assertEquals("[D][\u2718] Recurring Deadline!! (by: Dec 30 1900 6:00 pm [OVERDUE]) (Weekly)", // cross mark
                new RecurringDeadline("Recurring Deadline!!", LocalDateTime.of(1900, 12, 30, 18, 0),
                        Frequency.WEEKLY).toString());
        assertEquals("[D][\u2713] brunch (by: Today 11:59 pm) (Daily)", // tick mark
                new RecurringDeadline("brunch", LocalDate.now().atTime(23, 59), Frequency.DAILY,
                        LocalDate.now().atTime(18, 0), true, LocalDateTime.now(), true).toString());
    }
}
