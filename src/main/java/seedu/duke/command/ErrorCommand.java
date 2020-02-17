package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class ErrorCommand extends Command {
    private String errorMessage;

    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print(errorMessage);
    }

    @Override
    public boolean hasNextCommand() {
        return true;
    }
}
