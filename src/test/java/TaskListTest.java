import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void getTaskListTest() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        ArrayList<Task> testList = new ArrayList<Task>();
        tasks.newToDo("Study for CS2103");
        tasks.newDeadline("Test1", "2020-09-17 0000");
        tasks.newEvent("Test 2", "today 2pm");
        testList.add(new ToDo("Study for CS2103"));
        testList.add(new Deadline("Test1", "2020-09-17 0000"));
        testList.add(new Event("Test 2", "today 2pm"));

        Assertions.assertEquals(tasks.getTaskList().toString(), testList.toString());

    }

    @Test
    public void sizeTest() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.newToDo("Study for CS2103");
        tasks.newDeadline("Test1", "2020-09-17 0000");
        tasks.newEvent("Test 2", "today 2pm");
        tasks.newToDo("test-3");

        Assertions.assertEquals(tasks.getNumOfTasks(), 4);
    }

    @Test
    public void getTaskTest() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.newToDo("Study for CS2103");
        tasks.newDeadline("Test1", "2020-09-17 0000");
        tasks.newEvent("Test 2", "today 2pm");
        tasks.newToDo("test-3");
        tasks.newDeadline("new deadline", "2020-01-28");

        try {
            Assertions.assertEquals(tasks.getTask(4).toString(), "[D][N] new deadline (by: 28-01-2020 0000)");
        }
        catch (DukeException e) {
            Assertions.fail();
        }
    }
}
