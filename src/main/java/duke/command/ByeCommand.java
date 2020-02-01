package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ByeCommand extends Command {

    public ByeCommand() {
        super();
        this.isByeCommand = true;
    }

    public String execute(Storage storage, TaskList taskList) throws IOException {
        String message = "Bye. Hope to see you again soon!";
        Ui.printMessage(message);
        return message;
    }

}
