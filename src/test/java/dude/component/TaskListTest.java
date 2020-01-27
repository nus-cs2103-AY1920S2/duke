package dude.component;

import dude.task.Task;
import dude.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private Task task1 = new Todo("dude", false);
    private Task task2 = new Todo("blah blah", true);

    @Test
    void taskCount_equalToSizeOfGetAllTasks() {
        TaskList x = new TaskList();
        x.addTask(task1);
        x.addTask(task2);
        assertEquals(x.getAllTasks().size(), x.taskCount());
        for (int i = 0; i < 144; i++) {
            x.addTask(task1);
            assertEquals(x.getAllTasks().size(), x.taskCount());
        }
    }

    @Test
    void addTask_increaseTaskCountByOne() {
        TaskList x = new TaskList();
        int initialSize = x.taskCount();
        x.addTask(task1);
        assertEquals(initialSize + 1, x.taskCount());
    }

    @Test
    void removeTask_decreaseTaskCountByOne() {
        TaskList x = new TaskList();
        x.addTask(task1);
        int initialSize = x.taskCount();
        x.removeTask(1);
        assertEquals(initialSize - 1, x.taskCount());
    }
}