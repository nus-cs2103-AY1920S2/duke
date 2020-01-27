package task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    void test(){
        Task todo = new Todo("Task");
        String expectedFirst = "[T][Not Done] " + "Task";
        String actualFirst = todo.toString();
        String expectedSec = "T/0/Task\n";
        String actualSec = todo.toStringTasks();
        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSec, actualSec);
    }
}
