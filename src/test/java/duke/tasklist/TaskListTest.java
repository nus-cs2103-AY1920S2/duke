package duke.tasklist;

import duke.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {

    @Test
    public void addOneTask_thenGetTask_shouldReturnSameTask() {
        TaskList taskList = new TaskList();
        Task expected = new Task("Testing");
        taskList.addTask(expected);
        Assertions.assertEquals(expected, taskList.getTask(1));
    }

    @Test
    public void deleteTaskWithNoTask_shouldThrowDukeException() {
        TaskList taskList = new TaskList();
        Exception exception = assertThrows(DukeException.class, () -> {
            taskList.deleteTask(1);
        });

        String expectedMessage = "No task found in that index!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void deleteTaskWithTasks_shouldReturnTheTaskAtTheIndex() {
        TaskList taskList = new TaskList();
        Task expected = new Task("Testing");
        taskList.addTask(new Task("Wrong Task"));
        taskList.addTask(expected);
        taskList.addTask(new Task("Wrong Task"));

        Assertions.assertSame(expected, taskList.getTask(2));
    }

    @Test
    public void getAmountOfTask_shouldReturnCorrectNumber() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Wrong Task"));
        taskList.addTask(new Task("Wrong Task"));

        Assertions.assertEquals(2, taskList.getAmountOfTask());
    }
}
