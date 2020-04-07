package duke.command;

import duke.main.UiHandler;
import duke.utils.Storage;
import duke.utils.StorageStub;
import duke.utils.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    Storage storage = new StorageStub();
    TaskList taskList = new TaskList();
    UiHandler ui = new UiHandler();

    @Test
    public void executeTest() {
        Command command = new AddCommand();
        command.execute("todo go to school", ui, storage, taskList);
        assertEquals("I've added this task to the list:\n "
                + taskList.getList().get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list", ui.getResponse());
    }

}
