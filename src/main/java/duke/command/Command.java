package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList) throws DukeException;

    public boolean isExitCommand() {
        return false;
    }
}
