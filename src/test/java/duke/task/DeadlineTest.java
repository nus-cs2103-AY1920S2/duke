package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void formatSavingName() {
        assertEquals("deadline,assignment,true,2020-01-28\n",
                new Deadline("assignment", true, LocalDate.parse("2020-01-28")).formatSavingName());
    }

    @Test
    void testToString() {
        assertEquals("[D][Y] assignment (by: Jan 28 2020)",
                new Deadline("assignment", true, LocalDate.parse("2020-01-28")).toString());
    }
}