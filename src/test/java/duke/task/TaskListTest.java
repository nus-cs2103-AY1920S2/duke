package duke.task;

import static duke.util.MagicStrings.ERROR_TASK_CREATED_BEFORE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DuchessException;
import duke.util.Pair;

/**
 * JUnit test class for {@code TaskList}.
 */
public class TaskListTest {
    /**
     * Tests the default empty constructor of {@code TaskList}.
     */
    @Test
    public void testEmptyConstructor() {
        TaskList testTaskList = new TaskList();
        assertEquals(0, testTaskList.size());
    }

    /**
     * Tests the non-empty constructor of {@code TaskList}.
     */
    @Test
    public void testNonEmptyConstructor() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(new ToDo("Item 1"));
        taskArrayList.add(new ToDo("Item 2"));
        ArrayList<Task> archiveArrayList = new ArrayList<>();
        archiveArrayList.add(new ToDo("Archived item 1"));
        archiveArrayList.add(new ToDo("Archived item 2"));
        TaskList testTaskList = new TaskList(taskArrayList, archiveArrayList);
        assertEquals(2, testTaskList.size());
        assertEquals(2, testTaskList.archiveSize());
    }

    /**
     * Tests the {@code addTask} method of {@code TaskList}.
     */
    @Test
    public void addTask_validTask_success() {
        TaskList testTaskList = new TaskList();
        assertEquals(0, testTaskList.size());
        testTaskList.addTask(new ToDo("Hello World"));
        assertEquals(1, testTaskList.size());
    }

    /**
     * Tests the {@code addTask} method with an already existing task.
     */
    @Test
    public void addTask_existingTask_exceptionThrown() {
        TaskList testTaskList = new TaskList();
        assertEquals(0, testTaskList.size());
        testTaskList.addTask(new ToDo("Hello World"));
        assertEquals(1, testTaskList.size());
        try {
            testTaskList.addTask(new ToDo("Hello World"));
            fail();
        } catch (DuchessException e) {
            assertEquals(ERROR_TASK_CREATED_BEFORE, e.getMessage());
            testTaskList.completeTask(0);
            testTaskList.archive();
            assertEquals(0, testTaskList.size());
            testTaskList.addTask(new ToDo("Hello World"));
            assertEquals(1, testTaskList.size());
        }
    }

    /**
     * Tests the {@code getTask} method of {@code TaskList} when the list is not
     * empty.
     *
     * @throws DuchessException If index is out of bounds.
     */
    @Test
    public void getTask_nonEmptyTaskList_success() throws DuchessException {
        TaskList testTaskList = new TaskList();
        Task testTask = new ToDo("Placeholder");
        testTaskList.addTask(testTask);
        assertEquals(testTask, testTaskList.getTask(0));
    }

    /**
     * Tests the {@code getTask} method of {@code TaskList} when the list is empty.
     */
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

    /**
     * Tests the {@code removeTask} method of {@code TaskList} when the list is not
     * empty.
     *
     * @throws DuchessException If the index is out of bounds.
     */
    @Test
    public void removeTask_nonEmptyTaskList_success() throws DuchessException {
        TaskList testTaskList = new TaskList();
        Task testTask = new ToDo("Placeholder");
        assertEquals(0, testTaskList.size());
        testTaskList.addTask(testTask);
        assertEquals(1, testTaskList.size());
        testTaskList.removeTask(0);
        assertEquals(0, testTaskList.size());
    }

    /**
     * Tests the {@code removeTask} method of {@code TaskList} when the list is
     * empty.
     */
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

    /**
     * Tests the {@code completeTask} method of {@code TaskList} when the
     * {@code Task} in question is incomplete.
     *
     * @throws DuchessException If the index is out of bounds.
     */
    @Test
    public void completeTask_taskNotCompleted_success() throws DuchessException {
        TaskList testTaskList = new TaskList();
        Task testTask = new ToDo("Testing using this!");
        testTaskList.addTask(testTask);
        assertFalse(testTask.isCompleted());
        testTaskList.completeTask(0);
        assertTrue(testTask.isCompleted());
    }

    /**
     * Tests the {@code completeTask} method of {@code TaskList} when the
     * {@code Task} in question is already complete.
     */
    @Test
    public void completeTask_taskAlreadyCompleted_exceptionThrown() {
        try {
            TaskList testTaskList = new TaskList();
            Task testTask = new ToDo("Testing using this!", true, LocalDateTime.now(), LocalDateTime.now());
            testTaskList.addTask(testTask);
            assertTrue(testTask.isCompleted());
            testTaskList.completeTask(0);
            fail();
        } catch (DuchessException e) {
            assertEquals("You have already completed this task!", e.getMessage());
        }
    }

    /**
     * Tests the {@code getTaskArray()} method of {@code TaskList}.
     */
    @Test
    public void testGetTaskArray() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        taskArrayList.add(new ToDo("No!!"));
        TaskList testTaskList = new TaskList(taskArrayList, new ArrayList<>());
        assertEquals(taskArrayList, testTaskList.getTaskArray());
    }

    /**
     * Tests the {@code getArchiveArray()} method of {@code TaskList}.
     */
    @Test
    public void testGetArchiveArray() {
        ArrayList<Task> archiveArrayList = new ArrayList<>();
        archiveArrayList.add(new ToDo("No!!"));
        TaskList testTaskList = new TaskList(new ArrayList<>(), archiveArrayList);
        assertEquals(archiveArrayList, testTaskList.getArchiveArray());
    }

    /**
     * Tests the {@code find} method of {@code TaskList}.
     */
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

    /**
     * Tests the {@code archive} method of {@code TaskList}.
     */
    @Test
    public void testArchive() {
        TaskList testTaskList = new TaskList();
        Task testTask = new ToDo("Placeholder");
        testTaskList.addTask(testTask);
        assertEquals(1, testTaskList.size());
        assertEquals(0, testTaskList.archiveSize());
        testTask.completeTask();
        testTaskList.archive();
        assertEquals(0, testTaskList.size());
        assertEquals(1, testTaskList.archiveSize());
    }
}
