package duke.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class TaskListHistoryTest {

    @BeforeEach
    void setUp() {
        // Reset stack
        TaskListHistory.reset();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void update_nullTaskList_noUpdate() {
        // The stack of TaskList should only have the list at save file
        assertEquals(1, TaskListHistory.getStack().size());
        assertFalse(TaskListHistory.update(null));
        assertEquals(1, TaskListHistory.getStack().size());
    }

    @Test
    void update_oneTaskList_addTaskList() {
        assertEquals(1, TaskListHistory.getStack().size());
        assertTrue(TaskListHistory.update(new TaskList(new ArrayList<>())));
        assertEquals(2, TaskListHistory.getStack().size());
    }

    @Test
    void getPreviousState_noTaskListAddedToState_getInitialTaskList() {
        assertEquals(1, TaskListHistory.getStack().size());
        Optional<TaskList> previousTaskList = TaskListHistory.getPreviousState();
        if (previousTaskList.isPresent()) {
            assertEquals(TaskListHistory.getInitialTaskList(),
                    previousTaskList.get());
        } else {
            fail("No task list returned from previous state");
        }
    }

    @Test
    void getPreviousState_oneTaskListAddedToState_getInitialTaskList() {
        assertEquals(1, TaskListHistory.getStack().size());
        TaskList updatedTasks = new TaskList(Arrays.asList(
                new Todo("project meeting"),
                new Todo("read book")
        ));
        TaskListHistory.update(updatedTasks);
        assertEquals(2, TaskListHistory.getStack().size());
        // Should remove update task and get initial task list
        Optional<TaskList> previousTaskList = TaskListHistory.getPreviousState();
        if (previousTaskList.isPresent()) {
            assertEquals(TaskListHistory.getInitialTaskList(),
                    previousTaskList.get());
            assertEquals(1, TaskListHistory.getStack().size());
        } else {
            fail("No task list returned from previous state");
        }
    }
}