package duke.commands;

import java.util.List;

import duke.ui.Ui;
import duke.tasks.Task;
import duke.exceptions.DukeException;

public interface Command {
    void execute(String arg, List<Task> tasks, Ui ui) throws DukeException;
}

