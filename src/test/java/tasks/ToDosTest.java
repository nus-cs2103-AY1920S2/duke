package tasks;

import duke.tasks.Task;
import duke.tasks.ToDos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void todosTest() {
        Task t = new ToDos("assignment");
        String expected = "[T][\u2718] assignment";
        assertEquals(expected, t.toString());
    }
}
