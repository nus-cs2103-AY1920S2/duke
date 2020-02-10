package commands;

import exceptions.DukeException;
import storage.Storage;
import ui.Ui;
import tasklist.TaskList;

public class NullCommand extends Command {

    public NullCommand(String command) {
        super(command);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("");
    }
}