import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void createNewTask_notYetDone(){
        assertEquals(false, new Deadline("description", "2020-01-01").isTaskDone());
    }

    @Test
    public void markTaskAsDone_successful() {
        Deadline task = new Deadline("description", "2020-01-01");
        task.markAsDone();
        assertEquals(true, task.isTaskDone());
    }
}
