package model;

import exceptions.IllegalPositionException;
import org.junit.jupiter.api.Test;

import static TestUtils.TypicalTasks.TODO_TOPICS;
import static TestUtils.TypicalTasks.TODO_IP;
import static TestUtils.TypicalTasks.EVENT_W3LECTURE;
import static TestUtils.TypicalTasks.EVENT_W4LECTURE;
import static TestUtils.TypicalTasks.DL_ASSIGNMENT_1;
import static TestUtils.TypicalTasks.DL_ASSIGNMENT_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    private static final TaskList TEST_TASK_LIST = new TaskList(TODO_TOPICS,
            TODO_IP,
            EVENT_W4LECTURE,
            DL_ASSIGNMENT_1,
            DL_ASSIGNMENT_2);

    @Test
    public void testAddTask() {
        String lastResult = TEST_TASK_LIST.add(EVENT_W3LECTURE);

        assertEquals("Got it. I've added this task:\n"
                + "week 3 lecture at week 3 Friday\n"
                + "Now you have 6 tasks in the list.\n",
                lastResult);
        assertEquals(TEST_TASK_LIST.internalList.size(), 6);
    }

    @Test
    public void testRemoveTask() {
        try {
            String deleteResult = TEST_TASK_LIST.remove(4);

            assertEquals("Noted. I have removed this task:\n"
                            + " assignment 2 by week 5\n"
                            + "Now you have 5 tasks in the list.\n",
                    deleteResult);
            assertEquals(TEST_TASK_LIST.internalList.size(), 5);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFinishTask() {

        String deleteResult = null;
        try {
            deleteResult = TEST_TASK_LIST.markTaskAsDone(4);
            assertEquals("Nice! I've marked this task as done:\n"
                            + " week 3 lecture at week 3 Friday\n",
                    deleteResult);
            assertTrue(TEST_TASK_LIST.getTask(4).isDone());
        } catch (IllegalPositionException e) {
            fail();
        }
    }


}
