package commands;

import java.io.IOException;
import exceptions.DukeException;
import storage.Storage;
import ui.Ui;
import tasklist.TaskList;

public abstract class Command {

    String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract boolean isExit();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException ;
}
