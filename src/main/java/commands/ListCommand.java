package commands;

import tasks.TaskList;

import handlers.Storage;
import handlers.Ui;

/**
 * Handles the command when the user gives a 'list' command.
 * For the execute method, it is to print out all the tasks in the tasklist.
 */

public class ListCommand extends Command {
    public ListCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
