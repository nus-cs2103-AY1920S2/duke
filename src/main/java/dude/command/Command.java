package dude.command;

import dude.component.IStorage;
import dude.component.IUserInterface;
import dude.component.TaskList;

public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, IUserInterface ui, IStorage storage)
            throws CommandExecutionException;
}
