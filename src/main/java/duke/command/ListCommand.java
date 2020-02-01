package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    public String execute(Storage storage, TaskList taskList) throws IOException {
        String message = "Here are the tasks in your list: "
                + System.lineSeparator()
                + taskList.getTaskList();
        Ui.printMessage(message);
        return message;
    }

}
