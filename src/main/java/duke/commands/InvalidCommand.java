package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class InvalidCommand extends Command {
    private String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showInvalid(message);
    }
}
