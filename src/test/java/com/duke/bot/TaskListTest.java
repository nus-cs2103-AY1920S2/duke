package com.duke.bot;

import com.duke.bot.component.TaskList;
import com.duke.bot.task.DeadlineTask;
import com.duke.bot.task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void getSizeTest_noElements() {
        TaskList dummyTaskList = TaskList.createTaskList();
        assertEquals(dummyTaskList.getSize(), 0);
    }

    @Test
    public void getSizeTest_oneElement() {
        TaskList dummyTaskList = TaskList.createTaskList();
        dummyTaskList.addTask(Task.createTask("eat"));
        assertEquals(dummyTaskList.getSize(), 1);
    }

    @Test
    public void addTaskTest_addDeadLineTask() {
        TaskList dummyTaskList = TaskList.createTaskList();
        DeadlineTask dummyDeadlineTask = DeadlineTask.createDeadlineTask(
                "eat", LocalDate.of(1999,2, 23));
        dummyTaskList.addTask(dummyDeadlineTask);

        assertEquals(dummyTaskList.getSize(), 1);
        assertEquals(dummyTaskList.getTask(0), dummyDeadlineTask);
    }

    @Test
    public void getTaskTest_noException() {
        TaskList dummyTaskList = TaskList.createTaskList();
        DeadlineTask dummyDeadlineTask = DeadlineTask.createDeadlineTask(
                "eat", LocalDate.of(1999,2, 23));
        dummyTaskList.addTask(dummyDeadlineTask);

        assertEquals(dummyTaskList.getTask(0), dummyDeadlineTask);
    }

    @Test
    public void getTaskTest_OutOfBoundException() {
        TaskList dummyTaskList = TaskList.createTaskList();
        DeadlineTask dummyDeadlineTask = DeadlineTask.createDeadlineTask(
                "eat", LocalDate.of(1999,2, 23));
        dummyTaskList.addTask(dummyDeadlineTask);

        try {
            dummyTaskList.getTask(1);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }

}
