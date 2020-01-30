package duchess.task;

import duchess.exception.DuchessException;
import duchess.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void testEmptyConstructor() {
        TaskList testTaskList = new TaskList();
        assertEquals(0, testTaskList.size());
    }

    @Test
    public void testNonEmptyConstructor() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(new ToDo("Item 1"));
        taskArrayList.add(new ToDo("Item 2"));
        TaskList testTaskList = new TaskList(taskArrayList);
        assertEquals(2, testTaskList.size());
    }

    @Test
    public void testAddTask() {
        TaskList testTaskList = new TaskList();
        assertEquals(0, testTaskList.size());
        testTaskList.addTask(new ToDo("Hello World"));
        assertEquals(1, testTaskList.size());
    }

    @Test
    public void getTask_nonEmptyTaskList_success() {
        try {
            TaskList testTaskList = new TaskList();
            Task testTask = new ToDo("Placeholder");
            testTaskList.addTask(testTask);
            assertEquals(testTask, testTaskList.getTask(0));
        } catch (DuchessException e) {
            fail();
        }
    }

    @Test
    public void getTask_emptyTaskList_exceptionThrown() {
        try {
            TaskList testTaskList = new TaskList();
            testTaskList.getTask(0);
            fail();
        } catch (DuchessException e) {
            assertEquals("You're referring to a task which does not exist!", e.getMessage());
        }
    }

    @Test
    public void removeTask_nonEmptyTaskList_success() {
        try {
            TaskList testTaskList = new TaskList();
            Task testTask = new ToDo("Placeholder");
            assertEquals(0, testTaskList.size());
            testTaskList.addTask(testTask);
            assertEquals(1, testTaskList.size());
            testTaskList.removeTask(0);
            assertEquals(0, testTaskList.size());
        } catch (DuchessException e) {
            fail();
        }
    }

    @Test
    public void removeTask_emptyTaskList_exceptionThrown() {
        try {
            TaskList testTaskList = new TaskList();
            testTaskList.removeTask(0);
            fail();
        } catch (DuchessException e) {
            assertEquals("You're referring to a task which does not exist!", e.getMessage());
        }
    }

    @Test
    public void completeTask_taskNotCompleted_success() {
        try {
            TaskList testTaskList = new TaskList();
            Task testTask = new ToDo("Testing using this!");
            testTaskList.addTask(testTask);
            assertFalse(testTask.isCompleted());
            testTaskList.completeTask(0);
            assertTrue(testTask.isCompleted());
        } catch (DuchessException e) {
            fail();
        }
    }

    @Test
    public void completeTask_taskAlreadyCompleted_exceptionThrown() {
        try {
            TaskList testTaskList = new TaskList();
            Task testTask = new ToDo("Testing using this!", true);
            testTaskList.addTask(testTask);
            assertTrue(testTask.isCompleted());
            testTaskList.completeTask(0);
            fail();
        } catch (DuchessException e) {
            assertEquals("You have already completed this task!", e.getMessage());
        }
    }

    @Test
    public void testGetTaskArray() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(new ToDo("No!!"));
        TaskList testTaskList = new TaskList(taskArrayList);
        assertEquals(taskArrayList, testTaskList.getTaskArray());
    }

    @Test
    public void testFindSearchWords() {
        TaskList testTaskList = new TaskList();
        Task testTask = new ToDo("Placeholder");
        testTaskList.addTask(testTask);
        ArrayList<Pair<Task, Integer>> positiveResults = testTaskList.find("place");
        assertEquals(1, positiveResults.size());
        assertEquals(testTask, positiveResults.get(0).getFirst());
        assertEquals(0, positiveResults.get(0).getSecond());
        ArrayList<Pair<Task, Integer>> negativeResults = testTaskList.find("hello");
        assertEquals(0, negativeResults.size());
    }
}
