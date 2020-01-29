package command;

import exception.DukeException;
import io.Ui;
import task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    public boolean isExitCommand() {
        return false;
    }
}
