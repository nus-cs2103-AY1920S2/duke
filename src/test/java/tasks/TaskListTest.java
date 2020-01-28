package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void size() {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("Eat lunch"));
        assertEquals(1, taskList.size());
    }
}