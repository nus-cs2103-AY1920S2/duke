package duke.command;

import duke.main.UiHandler;
import duke.utils.Storage;
import duke.utils.StorageStub;
import duke.utils.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnknownCommandTest {

    Storage storage = new StorageStub();
    TaskList taskList = new TaskList();
    UiHandler ui = new UiHandler();

    @Test
    public void executeTest() {
        Command command = new UnknownCommand();
        command.execute("goalasdf", ui, storage, taskList);
        assertEquals("I don't understand what you want", ui.getResponse());
    }
}
