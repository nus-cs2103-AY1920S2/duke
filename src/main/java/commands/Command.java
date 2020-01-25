package commands;

import tasks.TaskList;

import handlers.Storage;
import handlers.Ui;

public abstract class Command {
    protected String command;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
