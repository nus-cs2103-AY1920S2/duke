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

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList testTaskList = new TaskList();
        testTaskList.add(TODO_TOPICS);
        testTaskList.add(TODO_IP);
        String lastResult = testTaskList.add(EVENT_W3LECTURE);

        assertEquals(lastResult, "Got it. I've added this task:\n"
                + "week 3 lecture at week 3 Friday\n"
                + "Now you have 3 tasks in the list.\n");
        assertEquals(testTaskList.internalList.size(), 3);
    }

    @Test
    public void testRemoveTask() throws IllegalPositionException {
        TaskList testTaskList = new TaskList();
        testTaskList.add(TODO_TOPICS);
        testTaskList.add(TODO_IP);
        testTaskList.add(EVENT_W3LECTURE);
        testTaskList.add(EVENT_W4LECTURE);
        testTaskList.add(DL_ASSIGNMENT_1);
        testTaskList.add(DL_ASSIGNMENT_2);

        String deleteResult = testTaskList.remove(5);

        assertEquals(deleteResult, "Noted. I have removed this task:\n"
                + " assignment 2 by week 5\n"
                + "Now you have 5 tasks in the list.\n");
        assertEquals(testTaskList.internalList.size(), 5);
    }
}
