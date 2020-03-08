package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import java.io.IOException;

/**
 * Command that closes Duke with a message.
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String action(TaskList taskList, Storage storage, Ui ui) throws IOException {
        return ui.exit();
    }
}
