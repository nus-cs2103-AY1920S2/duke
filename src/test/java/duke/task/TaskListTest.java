package duke.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {

    private TaskList list = new TaskList();

    @Test
    void getTasks() {
        assertEquals(new ArrayList<Task>(), list.getTasks());
    }

    @Test
    void addTask() {
        list.addTask(new Todo("Homework", false));
        assertEquals(1, list.getTasks().size());
    }

    @Test
    void deleteTask() {
        try {
            list.getTasks().clear();
            list.addTask(new Todo("Homework", false));
            list.deleteTask(1);
            assertEquals(0, list.getTasks().size());
        } catch (Exception e) {
            assertEquals("OOPS!!! Do you have this task number?", e.getMessage());
        }

        try {
            list.getTasks().clear();
            list.addTask(new Todo("Homework", false));
            list.deleteTask(2);
            assertEquals(0, list.getTasks().size());
        } catch (Exception e) {
            assertEquals("OOPS!!! Do you have this task number?", e.getMessage());
        }
    }
}