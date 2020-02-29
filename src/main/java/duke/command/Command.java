package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.task.TaskList;
import duke.exception.DukeException;

public abstract class Command {
    String input;
    boolean isExit;

    public Command(String input, boolean isExit) {
        this.input = input;
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}