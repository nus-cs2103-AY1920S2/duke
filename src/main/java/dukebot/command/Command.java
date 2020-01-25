package dukebot.command;

import dukebot.storage.Storage;
import dukebot.tasklist.TaskList;
import dukebot.ui.Ui;

public abstract class Command {
    abstract public void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}