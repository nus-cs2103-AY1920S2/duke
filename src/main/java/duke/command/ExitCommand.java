package duke.command;

import duke.Storage;
import duke.Ui;
import duke.common.Message;
import duke.task.TaskList;

public class ExitCommand extends Command {

    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command and display an exit message.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(Message.EXIT_MESSAGE);
    }
}