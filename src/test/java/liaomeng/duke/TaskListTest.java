package liaomeng.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getNoOfTasks_emptyList_reportZeroTask() {
        TaskList list = new TaskList();
        String expected = "Currently there is/are 0 task(s) in the list.";
        assertEquals(expected, list.getTotalNumOfTasks());
    }
}
