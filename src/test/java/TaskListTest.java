import Grapie.Functions.Storage;
import Grapie.Tasks.Task;
import Grapie.Commands.TaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void testIsBoolean() throws IOException {
        assertEquals(true, new TaskList(new ArrayList<Task>(), new Storage("data/test.txt")).isNumber("3"));
        assertEquals(false, new TaskList(new ArrayList<Task>(), new Storage("data/test.txt")).isNumber("a"));
    }
}
