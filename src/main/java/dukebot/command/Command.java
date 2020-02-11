package dukebot.command;

import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.ui.Ui;

/**
 * Command abstract class.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param appStorage AppStorage to accept.
     * @param ui Ui to accept.
     * @param storage Storage to accept.
     */
    public abstract void execute(AppStorage appStorage, Ui ui, Storage storage);

    /**
     * Asserts that the parameters are not null.
     *
     * @param appStorage AppStorage to accept.
     * @param ui Ui to accept.
     * @param storage Storage to accept.
     */
    public void assertExecuteNotNull(AppStorage appStorage, Ui ui, Storage storage) {
        assert appStorage != null;
        assert ui != null;
        assert storage != null;
    }

    /**
     * Returns true if command is ExitCommand, false otherwise.
     *
     * @return Whether this is an ExitCommand command.
     */
    public boolean isExit() {
        return false;
    }
}