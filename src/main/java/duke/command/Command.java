package duke.command;

import duke.ui.Ui;
import duke.task.Storage;
import duke.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
