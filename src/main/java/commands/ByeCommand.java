package commands;

import tasks.TaskList;

import handlers.Storage;
import handlers.Ui;

/**
 * Handles the command "bye", when the user wants to leave the application.
 * isExit is set to true to break the loop.
 * For the execute method, the tasks are saved into a text file.
 */

public class ByeCommand extends Command{
    public ByeCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showFarewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
