package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
 
class DeleteCommandTest {
    @Test
    void execute_validId_success() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        Command cmd = new DeleteCommand(1);
        cmd.execute(tasks);
        assertTrue(tasks.isEmpty());
    }

    @Test
    void execute_invalidId_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.add(new Todo("read book"));
            Command cmd = new DeleteCommand(2);
            cmd.execute(tasks);
            fail();
        } catch (DukeException e) {
            assertEquals("Task cannot be found.", e.getMessage());
        }
    }
}
