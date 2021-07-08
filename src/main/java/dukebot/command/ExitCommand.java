package dukebot.command;

import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        ui.sayLine(LineName.EXIT);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
