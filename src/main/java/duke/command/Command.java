package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.Ui;

/**
 * Represents user input in a meaningful way, so that it can be executed accordingly.
 */
public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui) throws DukeException;
}
