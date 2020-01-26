package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import main.java.TaskList;
import main.java.ToDo;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void sizeTest(){
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.getSize());
        tasks.addTask(new ToDo("test"));
        assertEquals(1, tasks.getSize());
    }

    @Test
    public void deleteTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("test"));
        tasks.deleteTask(0);
        assertEquals(0, tasks.getSize());
    }
}
