package duke;

import duke.storage.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TaskListTest {
    @Test
    public void runTest() {
        TaskList tl = new TaskList();
        tl.addTask(Arrays.asList("todo", "borrow", "book").toArray(String[]::new), new Storage("../data/duke.txt"));
        assert (tl.getSize() == 1);
    }
}
