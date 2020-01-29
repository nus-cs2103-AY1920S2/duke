package duke.task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    void toStringTest(){
        Task todo = new Todo("Task");
        String expectedFirst = "[T][Not Done] " + "Task";
        String actualFirst = todo.toString();
        assertEquals(expectedFirst, actualFirst);
    }

    @Test
    void toStringTasksTest(){
        Task todo = new Todo("Task");
        String expectedSec = "T/0/Task\n";
        String actualSec = todo.toStringTasks();
        assertEquals(expectedSec, actualSec);
    }
}
