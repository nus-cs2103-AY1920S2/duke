import core.TaskManager;
import org.junit.jupiter.api.Test;
import task.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {

    @Test
    public void testGetSize_emptyModel_isZero() {
        TaskManager taskManager = new TaskManager();
        taskManager.load(new ArrayList<Task>());
        assertEquals(0, taskManager.getSize());
    }
}
