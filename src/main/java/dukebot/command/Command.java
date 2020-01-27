package dukebot.command;

import dukebot.storage.Storage;
import dukebot.tasklist.TaskList;
import dukebot.ui.Ui;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList TaskList to accept.
     * @param ui Ui to accept.
     * @param storage Storage to accept.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns true if command is ExitCommand, false otherwise.
     *
     * @return Whether this is an ExitCommand command.
     */
    public boolean isExit() {
        return false;
    }
}