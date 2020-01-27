package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DukeException;
import duke.task.Todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
 
class DoneCommandTest {
    @Test
    void execute_validId_success() throws Exception {
        Storage storage = new Storage("data/duke.txt");
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        tasks.add(new Todo("read book"));
        Command cmd = new DoneCommand(1);
        cmd.execute(tasks, ui, storage);
        assertEquals("[T][Y] read book", tasks.get(1).toString());
    }

    @Test
    void execute_invalidId_exceptionThrown() {
        try {
            Storage storage = new Storage("data/duke.txt");
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            tasks.add(new Todo("read book"));
            Command cmd = new DoneCommand(2);
            cmd.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("Task cannot be found.", e.getMessage());
        }
    }

    @Test
    void execute_isDone_exceptionThrown() {
        try {
            Storage storage = new Storage("data/duke.txt");
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            tasks.add(new Todo("read book"));
            Command cmd = new DoneCommand(1);
            cmd.execute(tasks, ui, storage);
            cmd.execute(tasks, ui, storage);
            fail();
        } catch (DukeException e) {
            assertEquals("Task is already marked as done.", e.getMessage());
        }
    }
}
