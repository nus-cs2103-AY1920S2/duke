package duke.command;

import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A command to exit Duke.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String farewell = "Good day my friend! I'm here anytime you need me :)";
        return farewell;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
