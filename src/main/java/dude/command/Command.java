package dude.command;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public abstract class Command {
    /**
     * Returns true if the command causes the program to exit, false otherwise.
     *
     * @return true if the command causes the program to exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Dispatches to command that user input (parsed by Parser) gave, to interact with user,
     * and access and modify the current session's tasklist, and storage.
     *
     * @param tasks the current TaskList before the command is executed. Can be modified by execute.
     * @param ui the IUserInterface to report results of successful commands.
     * @param storage the IStorage from which the current session was loaded and to which the session will
     *                be saved to on an exiting command.
     * @throws CommandExecutionException if command is an impossible task.
     */
    public abstract void execute(TaskList tasks, IUserInterface ui, IStorage storage)
            throws CommandExecutionException;
}
