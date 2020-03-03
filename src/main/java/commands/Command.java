package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
