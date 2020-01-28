package commands;

import tasks.TaskList;

import handlers.Storage;
import handlers.Ui;

/**
 * The parent and abstract class of all the other command classes,
 * which contains the execute method and the isExit boolean
 */

public abstract class Command {
    protected String command;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
