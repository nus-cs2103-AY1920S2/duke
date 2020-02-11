package dukebot.command;

import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

/**
 * Command to reset storage.
 */
public class ResetStorageCommand extends Command {
    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        storage.clearStorage();
        ui.sayLine(LineName.RESET_STORAGE_SUCCESS);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
