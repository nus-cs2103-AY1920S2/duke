package duke;

import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TaskListTester {

    @Test
    public void doTask_completeTask_success() {
        Task t = new ToDo("mark complete");
        t.doTask();
        assertTrue(t.getIsDone());
    }

    @Test
    public void addSavedTaskToStored_addTask_success() {
        TaskList tl = new TaskList();
        Task t = new ToDo("Swim");
        tl.addSavedTaskToStored(t);
        assertEquals(1, tl.sizeOf());
    }

    @Test
    private void printTaskFromStored_completedToDoCommandSwim_success() {
        TaskList tl = new TaskList(); // new TaskList to store Tasks
        Task t = new ToDo("Swim"); // new Task for testing
        t.doTask(); // Task is marked as Complete and should give "V", not "X"

        String tickOrCross = tl.getTask(0).obtainStatusIcon();
        assertEquals("V", tickOrCross);

        tl.addSavedTaskToStored(t);
        String output = 1 + ". [" + tickOrCross + "] " + tl.getTask(1);
        assertEquals("1. [V] Swim", output);
    }

    @Test
    private void printTaskFromStored_notCompletedEventCommandFlyTime20190102_success() {
        TaskList tl = new TaskList(); // new TaskList to store Tasks
        Task t = new Event("Fly", "Jan 2 2019"); // new Task for testing
        // Task is not marked as Complete and should give "X", not "V"

        String tickOrCross = tl.getTask(0).obtainStatusIcon();
        assertEquals("X", tickOrCross);

        tl.addSavedTaskToStored(t);
        String output = 1 + ". [" + tickOrCross + "] " + tl.getTask(1);
        assertEquals("1. [X] Fly (at: Jan 2 2019)", output);
    }

    @Test
    private void printTaskFromStored_completedDeadlineCommandDinnerLimit2019302_success() {
        TaskList tl = new TaskList(); // new TaskList to store Tasks
        Task t = new Deadline("Dinner", "Mar 2 2019"); // new Task for testing
        t.doTask(); // Task is marked as Complete and should give "V", not "X"

        String tickOrCross = tl.getTask(0).obtainStatusIcon();
        assertEquals("V", tickOrCross);

        tl.addSavedTaskToStored(t);
        String output = 1 + ". [" + tickOrCross + "] " + tl.getTask(1);
        assertEquals("1. [X] Fly (at: Mar 2 2019)", output);
    }

    @Test
    public void sizeOf_sizeTwo_success() {
        TaskList tl = new TaskList();
        tl.addSavedTaskToStored(new ToDo("good"));
        tl.addSavedTaskToStored(new ToDo("bad"));
        assertEquals(2, tl.sizeOf());
    }

    @Test
    public void getTask_toDoGood_success() {
        TaskList tl = new TaskList();
        tl.addSavedTaskToStored(new ToDo("good"));
        String icon = (tl.getTask(0).getIsDone()) ? "V" : "X";
        assertEquals("Xgood", icon + tl.getTask(0).getCommand());
    }

}

