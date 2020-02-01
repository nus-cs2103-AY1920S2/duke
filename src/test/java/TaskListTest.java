import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    TaskList taskList;

    @BeforeEach
    public void before() {
        taskList = new TaskList();
    }

    @Test
    public void taskList_noList_createsEmptyTaskList() {
        assertEquals(taskList.size(), 0, "Creating task list with no arguments should create an empty task list");
    }

    @Test
    public void taskList_existingList_createsTaskListWithList() {
        List<Task> existingList = new ArrayList<>();
        existingList.add(new Todo("123"));
        TaskList newTaskList = new TaskList(existingList);
        assertEquals(newTaskList.size(), 1, "Creating task list with list should "
                + "create task list with tasks from existing list");
    }

    @Test
    public void getSize_getsSizeOfList() {
        assertEquals(taskList.size(), 0, "Size of empty list should be 0.");
        taskList.add(new Todo("123"));
        assertEquals(taskList.size(), 1, "Size of list should be 1.");
    }

    @Test
    public void delete_emptyList_doesNotDeleteTask() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.delete(0));
        assertEquals(taskList.size(), 0, "Deleting from an empty list should throw an error.");
    }

    @Test
    public void delete_hasTasks_deletesTask() {
        taskList.add(new Todo("123"));
        taskList.delete(0);
        assertEquals(taskList.size(), 0, "Deleting from a non-empty list should delete the task.");
    }

    @Test
    public void add_emptyList_addsTask() {
        taskList.add(new Todo("123"));
        assertEquals(taskList.size(), 1, "Adding to empty list should add the task.");
    }
}