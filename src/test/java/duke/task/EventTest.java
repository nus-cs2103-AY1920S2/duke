package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void formatSavingName() {
        assertEquals("event,concert,false,2020-01-28\n",
                new Event("concert", false, LocalDate.parse("2020-01-28")).formatSavingName());
    }

    @Test
    void testToString() {
        assertEquals("[E][N] concert (at: Jan 28 2020)",
                new Event("concert", false, LocalDate.parse("2020-01-28")).toString());
    }
}