package dude.command;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public class ByeCommand extends Command {
    /**
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
     * Closes resources used by Dude (which is only IUserInterface).
     * Never throws CommandExecutionException.
     *
     * @param tasks the current TaskList before the command is executed. Can be modified by execute.
     * @param ui the IUserInterface to report results of successful commands.
     * @param storage the IStorage from which the current session was loaded and to which the session will
     *  be saved to on an exiting command.
     */
    @Override
    public void execute(TaskList tasks, IUserInterface ui, IStorage storage) {
        storage.saveSession(ui, tasks);
        ui.respond("See ya!");
        ui.close();
    }
}
