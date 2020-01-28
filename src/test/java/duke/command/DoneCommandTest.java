package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DoneCommandTest {

    @Test
    public void testExecute() {
        try {
            Storage storage = new Storage("data/duke.txt");
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            tasks.addTask(new ToDo("read book"));
            Command cmd = new DoneCommand(0);
            cmd.execute(tasks, ui, storage);
            assertEquals("Y", tasks.getTask(0).getStatusIcon());
        } catch (DukeException e) {
            assertEquals("OOPS!!! Index is out of bounds.", e.getMessage());
        }
    }
}
