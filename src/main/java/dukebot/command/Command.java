package dukebot.command;

import dukebot.Storage;
import dukebot.Ui;
import dukebot.tasklist.TaskList;

public abstract class Command {
    abstract public void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}