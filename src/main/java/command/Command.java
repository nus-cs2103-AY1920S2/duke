package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command object which can be executed.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
