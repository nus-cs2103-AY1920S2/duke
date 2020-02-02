package dukebot.command;

import dukebot.storage.Storage;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

public class ResetStorageCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.clearStorage();
        ui.sayLine(LineName.RESET_STORAGE_SUCCESS);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
