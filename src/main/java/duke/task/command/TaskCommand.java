package duke.task.command;

import duke.DukeException;
import duke.Storage;
import duke.common.command.Command;
import duke.task.TaskList;

public abstract class TaskCommand extends Command {

    public abstract String execute(TaskList expenses, Storage storage)
            throws DukeException;
}