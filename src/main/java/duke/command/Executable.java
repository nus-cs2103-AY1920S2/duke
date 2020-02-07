package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;

public interface Executable {

    public String execute(TaskList tasks, Storage storage)
            throws DukeException;
}