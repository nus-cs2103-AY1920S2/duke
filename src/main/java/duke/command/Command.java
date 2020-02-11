package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {
    public abstract boolean isExit();
    public abstract void execute(TaskList tasklist, Ui ui, Storage storage);
}
