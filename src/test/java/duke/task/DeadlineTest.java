package duke.task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {
    @Test
    public void encoderTest() {
        assertEquals(2, 2);
        duke.task.Deadline test = new Deadline("~`48dgsfd", true, "by 2020-02-01");
        assertEquals("D:~`48dgsfd:1:by 2020-02-01\n", test.encoder());
    }

    @Test
    public void typeTest() {
        duke.task.Deadline test = new Deadline("~`48dgsfd", true, "by 2020-02-01");
        assertEquals(Task.TaskType.deadline, test.getTaskType());
    }
}
