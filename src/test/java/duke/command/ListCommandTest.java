package duke.command;

import duke.main.UiHandler;
import duke.utils.Storage;
import duke.utils.StorageStub;
import duke.utils.TaskList;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {

    Storage storage = new StorageStub();
    TaskList taskList = new TaskList();
    UiHandler ui = new UiHandler();

    @Test
    public void executeEmptyListTest() {
        Command command = new ListCommand();
        command.execute("list", ui, storage, taskList);
        assertEquals(taskList.toString(), ui.getResponse());
    }

    @Test
    public void executeListTest() {
        Command command = new ListCommand();
        try {
            taskList.addToList("go to school", "todo");
            taskList.addToList("go to hell", "todo");
            command.execute("list", ui, storage, taskList);
            assertEquals(taskList.toString(), ui.getResponse());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
