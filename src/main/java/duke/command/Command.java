package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract boolean execute(TaskList taskList, Ui ui) throws DukeException;
}
