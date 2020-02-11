package duke.commands;

import duke.tasks.*;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class ListCommand implements Command {


    /**
     * Prints out the current list of tasks
     * @param tasks List of current tasks
     * @throws DukeException If input format is wrong. Not used here
     */
    public static String execute(TaskList tasks) {
        return tasks.printTaskList();
    }
}
