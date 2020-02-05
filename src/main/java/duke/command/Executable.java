package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public interface Executable {

    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException;
}