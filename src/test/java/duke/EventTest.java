package duke;

import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void generateSaveFileEntry_validEvent_returnsEntry() {
        assertEquals("E | 0 | project meeting | 2020-01-10\n",
                new Event("project meeting", LocalDate.parse("2020-01-10")).generateSaveFileEntry());
        assertEquals("E | 1 | project meeting | 2020-10-01\n",
                new Event("project meeting", LocalDate.parse("2020-10-01"), true).generateSaveFileEntry());
        assertEquals("E | 0 | project meeting | 2020-10-01\n",
                new Event("project meeting", LocalDate.parse("2020-10-01"), false).generateSaveFileEntry());
    }

    @Test
    void toString_validEvent_returnsString() {
        assertEquals("[E] [X] project meeting (at: Jan 10 2020)",
                new Event("project meeting", LocalDate.parse("2020-01-10")).toString());
        assertEquals("[E] [V] project meeting (at: Jan 10 2020)",
                new Event("project meeting", LocalDate.parse("2020-01-10"), true).toString());
    }
}