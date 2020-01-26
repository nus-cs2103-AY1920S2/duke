package duke.command;

import duke.ui.Ui;
import duke.task.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
    }
}
