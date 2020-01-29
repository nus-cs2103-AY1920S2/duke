package duke;

import duke.command.AddCommand;
import duke.exception.DukeException;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void DukeExceptionThrowIfTest() {

        boolean assertionThrown1 = false;
        boolean assertionThrown2 = false;

        try {
            DukeException.throwIf(false, "");
        } catch (DukeException e) {
            assertionThrown1 = true;
        }

        try {
            DukeException.throwIf(true, "");
        } catch (DukeException e) {
            assertionThrown2 = true;
        }

        assert(assertionThrown1 == false);
        assert(assertionThrown2 == true);
    }

    @Test
    public void addCommandTest() throws DukeException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui(System.in);
        assertEquals(taskList.size(), 0);
        new AddCommand(Task.TaskType.TASK_TYPE_TODO, "Test Todo").execute(taskList, ui);
        assertEquals(taskList.size(), 1);
        new AddCommand(Task.TaskType.TASK_TYPE_EVENT, "Test Event /at Test Location").execute(taskList, ui);
        assertEquals(taskList.size(), 2);
        new AddCommand(Task.TaskType.TASK_TYPE_DEADLINE, "Test Deadline /by 2020-01-01").execute(taskList, ui);
        assertEquals(taskList.size(), 3);
    }

    @Test
    public void dateTimeTest() throws DukeException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui(System.in);
        new AddCommand(Task.TaskType.TASK_TYPE_DEADLINE, "Test Deadline /by 2020-01-01").execute(taskList, ui);
        assertEquals(taskList.size(), 1);

        boolean assertionThrown = false;

        try {
            new AddCommand(Task.TaskType.TASK_TYPE_DEADLINE, "Test Deadline /by 2020991-01").execute(taskList, ui);
        } catch (DukeException e) {
            assertionThrown = true;
        }

        assertEquals(taskList.size(), 1);
        assert(assertionThrown);
    }
}