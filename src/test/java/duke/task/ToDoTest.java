package duke.task;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToDoTest {
    @Test
    public void encoderTest() {
        assertEquals(2, 2);
        duke.task.ToDo test = new ToDo(".....", true);
        assertEquals("T:.....:1\n", test.encoder());
    }

    @Test
    public void typeTest() {
        duke.task.ToDo test = new ToDo(".....", true);
        assertEquals(Task.TaskType.toDo, test.getTaskType());
    }
}
