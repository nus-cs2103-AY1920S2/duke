package duke.commands;

import duke.ui.Ui;
import duke.tasks.TaskList;
import duke.exceptions.DukeException;

public interface Command {
    void execute(String arg, TaskList tasks, Ui ui) throws DukeException;
}

