package commands;

import exceptions.DukeException;
import storage.Storage;
import ui.Ui;
import tasklist.TaskList;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand(String command) {
        super(command);
    }

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        System.out.println("Bye. Hope to see you again soon!");
    }
}