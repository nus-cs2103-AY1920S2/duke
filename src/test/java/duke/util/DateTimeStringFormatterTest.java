package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for {@code DateTimeStringFormatter}.
 */
public class DateTimeStringFormatterTest {
    /**
     * Tests the formatter functionalities.
     */
    @Test
    public void testFormatter() {
        LocalDateTime overdueTime = LocalDate.now().atTime(0, 0, 0);

        // Testing overdue dates
        assertEquals("Today 12:00 am", DateTimeStringFormatter.formatDateTime(overdueTime, true)); // isCompleted works
        assertEquals("Today 12:00 am [OVERDUE]", DateTimeStringFormatter.formatDateTime(overdueTime, false));
        assertEquals("Yesterday 12:00 am [OVERDUE]", DateTimeStringFormatter.formatDateTime(
                overdueTime.minusDays(1), false));

        // The next test will fail if two days ago was in a different year
        if (overdueTime.minusDays(2).getYear() == overdueTime.getYear()) {
            assertEquals(overdueTime.minusDays(2).format(DateTimeFormatter.ofPattern("MMM d "))
                    + "12:00 am [OVERDUE]", DateTimeStringFormatter.formatDateTime(overdueTime.minusDays(2), false));
        } else {
            assertEquals(overdueTime.minusDays(2).format(DateTimeFormatter.ofPattern("MMM d yyy"))
                    + "12:00 am [OVERDUE]", DateTimeStringFormatter.formatDateTime(overdueTime.minusDays(2), false));
        }
        // Testing difference in year
        assertEquals(overdueTime.minusYears(1).format(DateTimeFormatter.ofPattern("MMM d yyyy "))
                + "12:00 am [OVERDUE]", DateTimeStringFormatter.formatDateTime(overdueTime.minusYears(1), false));

        LocalDateTime notOverdueTime = LocalDate.now().atTime(23, 59, 59);

        // Testing non-overdue dates
        assertEquals("Today 11:59 pm", DateTimeStringFormatter.formatDateTime(notOverdueTime, false));
        assertEquals("Tomorrow 11:59 pm", DateTimeStringFormatter.formatDateTime(notOverdueTime.plusDays(1), false));
        assertEquals(notOverdueTime.plusDays(2).format(DateTimeFormatter.ofPattern("EEE ")) + "11:59 pm",
                DateTimeStringFormatter.formatDateTime(notOverdueTime.plusDays(2), false));
        assertEquals(notOverdueTime.plusDays(20).format(DateTimeFormatter.ofPattern("MMM d ")) + "11:59 pm",
                DateTimeStringFormatter.formatDateTime(notOverdueTime.plusDays(20), false));
        assertEquals(notOverdueTime.plusYears(1).format(DateTimeFormatter.ofPattern("MMM d yyyy ")) + "11:59 pm",
                DateTimeStringFormatter.formatDateTime(notOverdueTime.plusYears(1), false));
    }

}
