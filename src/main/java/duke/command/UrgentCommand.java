package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class UrgentCommand extends Command {

    public UrgentCommand() {
        super();
    }

    public String execute(Storage storage, TaskList taskList) throws IOException {
        String message = "Here are urgent tasks in your list! "
                + System.lineSeparator()
                + System.lineSeparator()
                + taskList.getUrgentTasks();
        Ui.printMessage(message);
        return message;
    }

}
