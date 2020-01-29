package duke.command;

import duke.exception.DukeException;
import duke.io.Ui;
import duke.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    public boolean isExitCommand() {
        return false;
    }
}
