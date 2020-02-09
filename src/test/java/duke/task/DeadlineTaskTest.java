package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTaskTest {
    @Test
    public void testToString() {
        DeadlineTask task;
        
        task = new DeadlineTask("eat lunch", LocalDateTime.of(2020, 5, 31, 16, 20));
        assertEquals("[D][ ] eat lunch (by: Sun, 31 May 2020, 16:20)", task.toString());

        task = new DeadlineTask("eat lunch", LocalDateTime.of(2020, 5, 31, 16, 20));
        task.markAsCompleted();
        assertEquals("[D][X] eat lunch (by: Sun, 31 May 2020, 16:20)", task.toString());

        task = new DeadlineTask("eat lunch", LocalDateTime.of(2020, 5, 31, 16, 20), true);
        assertEquals("[D][X] eat lunch (by: Sun, 31 May 2020, 16:20)", task.toString());

        task = new DeadlineTask("eat lunch", LocalDateTime.of(2020, 5, 31, 16, 20), false);
        assertEquals("[D][ ] eat lunch (by: Sun, 31 May 2020, 16:20)", task.toString());
    }

    @Test
    public void testToStringDelimited() {
        DeadlineTask task;

        task = new DeadlineTask("eat lunch", LocalDateTime.of(2020, 5, 31, 16, 20));
        assertEquals("D |   | eat lunch | Sun, 31 May 2020, 16:20", task.toStringDelimited());

        task = new DeadlineTask("eat lunch", LocalDateTime.of(2020, 5, 31, 16, 20));
        task.markAsCompleted();
        assertEquals("D | X | eat lunch | Sun, 31 May 2020, 16:20", task.toStringDelimited());
    }
}
