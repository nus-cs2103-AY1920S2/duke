package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
 
class DeleteCommandTest {
    @Test
    void execute_validId_success() throws Exception {
        Storage storage = new Storage("data/duke.txt");
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        tasks.add(new Todo("read book"));
        Command cmd = new DeleteCommand(1);
        cmd.execute(tasks, ui, storage);
        assertTrue(tasks.isEmpty());
    }

    @Test
    void execute_invalidId_exceptionThrown() {
        try {
            Storage storage = new Storage("data/duke.txt");
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            tasks.add(new Todo("read book"));
            Command cmd = new DeleteCommand(2);
            cmd.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("Task cannot be found.", e.getMessage());
        }
    }
}
