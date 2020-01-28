package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        // Just display goodbye message
        ui.showGoodbye();
        return tasks; // Don't update anything
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
