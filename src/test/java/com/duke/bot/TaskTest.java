package com.duke.bot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    public void getTaskNameTest() {
        Task task = Task.createTask("Task name 1");
        assertEquals(task.getTaskName(), "Task name 1");
    }

    @Test
    public void markDoneTest() {
        Task task = Task.createTask("Task 1");
        task.markDone();

        assertTrue(task.isDone());
    }

}
