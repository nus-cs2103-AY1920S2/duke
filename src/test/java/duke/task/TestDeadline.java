package duke.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDeadline {
    @Test
    public void testSerialize() {
        assertEquals("D,0,homework,2020-02-06T23:59",
                new Deadline("homework", LocalDateTime.parse("2020-02-06T23:59")).serialize());

        Deadline deadline = new Deadline("group assignment", LocalDateTime.parse("2020-02-23T23:59"));
        deadline.markAsDone();
        assertEquals("D,1,group assignment,2020-02-23T23:59", deadline.serialize());
    }

    @Test
    public void testToString() {
        assertEquals("[D][✘] homework (by: 06 Feb 2020, 11:59PM)",
                new Deadline("homework", LocalDateTime.parse("2020-02-06T23:59")).toString());

        Deadline deadline = new Deadline("group assignment", LocalDateTime.parse("2020-02-23T23:59"));
        deadline.markAsDone();
        assertEquals("[D][✔] group assignment (by: 23 Feb 2020, 11:59PM)", deadline.toString());
    }
}
