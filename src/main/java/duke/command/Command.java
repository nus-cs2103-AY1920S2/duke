package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;

public abstract class Command {
    public abstract String execute(Ui ui, TaskStorage storage);
}
