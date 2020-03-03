import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    TaskList taskList = new TaskList();

    @Test
    void getSize() {
        taskList.addTask(new Task( Duke.Tasks.todo, "read book" ));
        assertEquals(1, taskList.getSize());
    }

    @Test
    void isEmpty() {
        assertTrue(taskList.isEmpty());
    }
}