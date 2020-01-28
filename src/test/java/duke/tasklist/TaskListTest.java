package duke.tasklist;

import duke.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>TaskListTest Class</h1>
 * Test for the TaskList class
 *
 * @author  Eng Xuan En
 */
public class TaskListTest {

    /**
     * Test whether methods, addTask and getTask, work correctly.
     */
    @Test
    public void addOneTask_thenGetTask_shouldReturnSameTask() {
        TaskList taskList = new TaskList();
        Task expected = new Task("Testing");
        taskList.addTask(expected);
        Assertions.assertEquals(expected, taskList.getTask(1));
    }

    /**
     * Test if delete task without any task in taskList will throw Duke Exception or not.
     */
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

    /**
     * Test if getTask method return the correct task at the index.
     */
    @Test
    public void deleteTaskWithTasks_shouldReturnTheTaskAtTheIndex() {
        TaskList taskList = new TaskList();
        Task expected = new Task("Testing");
        taskList.addTask(new Task("Wrong Task"));
        taskList.addTask(expected);
        taskList.addTask(new Task("Wrong Task"));

        Assertions.assertSame(expected, taskList.getTask(2));
    }

    /**
     * Test if the method, getAmountOfTask, will return correct number.
     */
    @Test
    public void getAmountOfTask_shouldReturnCorrectNumber() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Wrong Task"));
        taskList.addTask(new Task("Wrong Task"));

        Assertions.assertEquals(2, taskList.getAmountOfTask());
    }
}
