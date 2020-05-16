import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void AddTest() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("a"));
        tasks.add(new ToDo("b"));
        assertEquals(tasks.size(), 2);
    }
}
