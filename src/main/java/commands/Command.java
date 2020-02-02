package commands;

import dukeexception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();

    public static final String LINE = "____________________________________________________________\n";
}
