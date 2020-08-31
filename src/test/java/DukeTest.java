import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testDeadlineFormat() {
        Deadline deadline = new Deadline("essay", "2020-01-29 2359");
        assertEquals("[D][" + "\u2718" + "] essay (by: Jan 29 2020 11.59pm)",
                deadline.getTaskDisplay());
    }

    @Test
    public void testEventFormat() {
        Event event = new Event("driving class", "12/5/2019 1300");
        assertEquals("[E][" + "\u2718" + "] driving class (at: May 12 2019 1.00pm)",
                event.getTaskDisplay());
    }

    @Test
    public void testParser() {
        TaskList dummy = new TaskList();
        assertEquals(false, new Parser().parseCommand("bye bye", dummy));
    }
}
