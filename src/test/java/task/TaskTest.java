package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import exception.DukeException;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void taskCreation() {
        try {
            Task todo = Task.newTask("todo done with j-unit");
            assertEquals("[T][N] done with j-unit", todo.toString());
            Task deadline = Task.newTask("deadline return book /by 19-12-2019 4pm");
            assertEquals("[D][N] return book (by: 19 Dec 2019 16:00)", deadline.toString());
            Task event = Task.newTask("event project meeting /at 02/11/2020 2359");
            assertEquals("[E][N] project meeting (at: 02 Nov 2020 23:59)", event.toString());
            Task period =
                    Task.newTask(
                            "period TOTO 4D BIG HUAT /start 10/02/2020 0000 /end 12/02/2020 2359");
            assertEquals(
                    "[P][N] TOTO 4D BIG HUAT (start: 10 Feb 2020 00:00, end: 12 Feb 2020 23:59)",
                    period.toString());
        } catch (DukeException err) {

        }
    }

    @Test
    public void taskCreationExceptions() {
        Exception periodException =
                assertThrows(
                        DukeException.class,
                        () -> {
                            Task.newTask(
                                    "period TOTO 4D BIG HUAT /start 14/02/2020 0000 /end 12/02/2020 2359");
                        });
        assertEquals(
                "Oppsie doodle: End dates and time must be after start dates and time",
                periodException.getMessage());

        Exception noTypesException =
                assertThrows(
                        DukeException.class,
                        () -> {
                            Task.newTask(
                                    "TOTO 4D BIG HUAT /start 14/02/2020 0000 /end 12/02/2020 2359");
                        });
        assertEquals("Oppsie doodle: Dear user, No accepted types present", noTypesException.getMessage());
    }

    @Test
    public void taskMarking() {
        try {
            Task event = Task.newTask("event project meeting /at 02/11/2020 2359");
            assertEquals(false, event.isDone());
            event.setDone();
            assertEquals(true, event.isDone());
        } catch (DukeException err) {
        }
    }
}
