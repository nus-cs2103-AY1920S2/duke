package tasks;

import duke.tasks.Todo;
import duke.tasks.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    @Test
    void size() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Eat lunch"));
        assertEquals(1, taskList.size());
    }
}