package dude.command;

import dude.component.IUserInterface;
import dude.component.TaskList;

public class ByeCommand extends Command {
    /**
     * Overrides default implementation of returning true as ByeCommand is supposed to cause Dude.serve() to exit.
     *
     * @return true since ByeCommand causes Dude to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Attempts to save current session TaskList state to storage.
     * Bids farewell to users.
     * Never throws CommandExecutionException.
     *
     * @param tasks the current TaskList before the command is executed. Can be modified by execute.
     * @param ui the IUserInterface to report results of successful commands.
     */
    @Override
    public void execute(TaskList tasks, IUserInterface ui) {
        ui.respond("See ya!");
    }
}
