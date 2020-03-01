package commands;

import java.io.IOException;
import exception.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    public Command() {
    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    /**
     * Executes the command.
     *
     * @param tasks is task list for in-memory.
     * @param ui is ui to display to user.
     * @param storage is file where data is written to and read from.
     * @return response to user.
     * @throws DukeException is exception when dealing with Duke.
     * @throws IOException is exception for file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        return "";
    }
}
