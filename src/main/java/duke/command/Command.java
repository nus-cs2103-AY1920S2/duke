package duke.command;

import duke.Ui.Ui;
import duke.storage.TaskStorage;
import exception.IllegalTextException;

public abstract class Command {
    public abstract String execute(Ui ui, TaskStorage storage) throws IllegalTextException;
}
