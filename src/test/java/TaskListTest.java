import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void createEvent_noDescription_exceptionThrown() {
        try {
            assertEquals(new Event("description", "2020-01-01", "1000"), TaskListStub.createEvent("event"));
            fail();
        } catch (GooseEmptyDescriptionException e) {
            assertEquals("Honk! Description of an event cannot be empty.", e.getMessage());
        }  catch (GooseIllegalFormatException e) {
            fail();
        }
    }

    @Test
    public void createEvent_noAt_exceptionThrown() {
        try {
            assertEquals(new Event("description", "2020-01-01", "1000"), TaskListStub.createEvent("event description /at"));
            fail();
        } catch (GooseIllegalFormatException e) {
            assertEquals("Honk! No event date specified.", e.getMessage());
        } catch (GooseEmptyDescriptionException e) {
            fail();
        }
    }

    @Test
    public void testCreateEvent() {
        try {
            assertEquals("[E][N] description (at: Jan 1 2020, 10.00am)", TaskListStub.createEvent("event description /at 1/1/2020 1000").toString());
        } catch (GooseEmptyDescriptionException | GooseIllegalFormatException e) {
            fail();
        }
    }
}
