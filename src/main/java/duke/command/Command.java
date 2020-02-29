package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.Storage;

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