import duke.tasks.Task;
import duke.tasks.Event;
import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventTest() {
        try {
            Task task = new Event("test", "2020-01-01", "23:59");
            String output = "[E][✗] test (at: Jan 1 2020, 23:59)";
            assertEquals(output, task.toString());
            String output1 = "E | 0 | test | 2020-01-01 | 23:59\n";
            assertEquals(output1, task.formatText());
        } catch (DukeException ignored) {
        }
    }
}
