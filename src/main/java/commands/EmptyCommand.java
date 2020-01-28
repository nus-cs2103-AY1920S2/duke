package commands;

import tasks.TaskList;

import handlers.Storage;
import handlers.Ui;

/**
 * Handles the error in the parse method,
 * as there is a need to return something.
 */

public class EmptyCommand extends Command {
    public EmptyCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    @Override
    public boolean isExit() {
        return false;
    }
}
