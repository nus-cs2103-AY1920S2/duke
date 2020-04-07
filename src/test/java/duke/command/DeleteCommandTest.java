package duke.command;

import duke.main.UiHandler;
import duke.utils.Storage;
import duke.utils.StorageStub;
import duke.utils.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

    Storage storage = new StorageStub();
    TaskList taskList = new TaskList();
    UiHandler ui = new UiHandler();

    @Test
    public void executeTest() {
        Command command = new DeleteCommand();
        command.execute("delete 1", ui, storage, taskList);
        assertEquals("No such task number", ui.getResponse());
    }
}
