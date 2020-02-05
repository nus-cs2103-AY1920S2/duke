package duke.command;

import duke.Storage;
import duke.ui.Ui;
import duke.common.Message;
import duke.task.TaskList;

public class ListCommand extends Command {

    /**
     * Executes the command and lists all the tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = Message.LIST_MESSAGE + "\n"
                + tasks.toString();
        ui.showMessage(output);
    }
}