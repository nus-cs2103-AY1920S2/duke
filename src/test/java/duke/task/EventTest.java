package duke.task;

import duke.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
 
class EventTest {
    @Test
    void constructorTest_validDateTime_success() throws Exception {
        Event event = new Event("project meeting", "2020-01-27 14:00-16:00");
        assertEquals("project meeting", event.description);
        assertEquals("2020-01-27", event.date.toString());
        assertEquals("14:00", event.startTime.toString());
        assertEquals("16:00", event.endTime.toString());
    }

    @Test
    void constructorTest_invalidDateTimeFormat_exceptionThrown() {
        try {
            Event event = new Event("project meeting", "27-01-2020 14:00-16:00");
            fail();
        } catch (DukeException e) {
            assertEquals("Incorrect date or time format. Format required: yyyy-mm-dd hh:mm-hh:mm", e.getMessage());
        }
    }
    
    @Test
    void constructorTest_missingDateTime_exceptionThrown() {
        try {
            Event event = new Event("project meeting", "2020-01-27 14:00");
            fail();
        } catch (DukeException e) {
            assertEquals("Incorrect date or time format. Format required: yyyy-mm-dd hh:mm-hh:mm", e.getMessage());
        }
    }

    @Test
    void constructorTest_startTimeAfterEndTime_exceptionThrown() {
        try {
            Event event = new Event("project meeting", "2020-01-27 16:00-14:00");
            fail();
        } catch (DukeException e) {
            assertEquals("Start time cannot be after end time.", e.getMessage());
        }
    }

    @Test
    void constructorTest2() throws Exception {
        Event event = new Event("project meeting", "2020-01-27 14:00-16:00", true);
        assertEquals("project meeting", event.description);
        assertEquals("2020-01-27", event.date.toString());
        assertEquals("14:00", event.startTime.toString());
        assertEquals("16:00", event.endTime.toString());
        assertTrue(event.isDone);
    }

    @Test
    void getDate() throws Exception {
        Event event = new Event("project meeting", "2020-01-27 14:00-16:00");
        assertEquals("2020-01-27", event.getDate().toString());
    }
    
    @Test
    void formatToSave() throws Exception {
        Event event = new Event("project meeting", "2020-01-27 14:00-16:00");
        assertEquals("E | 0 | project meeting | 2020-01-27 14:00-16:00", event.formatToSave());
    }

    @Test
    void stringConversion() throws Exception {
        Event event = new Event("project meeting", "2020-01-27 14:00-16:00");
        assertEquals("[E][N] project meeting (at: 27 Jan 2020 14:00-16:00)", event.toString());
    }
}
