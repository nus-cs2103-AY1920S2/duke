package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
 
class DoneCommandTest {
    @Test
    void execute_validId_success() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        Command cmd = new DoneCommand(1);
        cmd.execute(tasks);
        assertEquals("[T][Y] read book", tasks.get(1).toString());
    }

    @Test
    void execute_invalidId_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("read book"));
            Command cmd = new DoneCommand(2);
            cmd.execute(tasks);
            fail();
        } catch (DukeException e) {
            assertEquals("Task cannot be found.", e.getMessage());
        }
    }

    @Test
    void execute_isDone_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("read book"));
            Command cmd = new DoneCommand(1);
            cmd.execute(tasks);
            cmd.execute(tasks);
            fail();
        } catch (DukeException e) {
            assertEquals("Task is already marked as done.", e.getMessage());
        }
    }
}
