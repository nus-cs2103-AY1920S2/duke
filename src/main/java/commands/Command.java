package commands;

import java.io.IOException;

import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    public Command() {}

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        return "";
    }
}
