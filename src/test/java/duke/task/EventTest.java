package duke.task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {
    @Test
    public void encoderTest() {
        assertEquals(2, 2);
        duke.task.Event test = new Event("~`48dgsfd", true, "at 2020-02-01");
        assertEquals("E:~`48dgsfd:1:at 2020-02-01\n", test.encoder());
    }

    @Test
    public void typeTest() {
        duke.task.Event test = new Event("~`48dgsfd", true, "at 2020-02-01");
        assertEquals(Task.TaskType.event, test.getTaskType());
    }
}
