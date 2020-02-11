package duke.commands;

import duke.tasks.*;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class ListCommand extends Command {


    /**
     * Prints out the current list of tasks to terminal
     * @param description Description of task
     * @param tasks List of current tasks
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong. Not used here
     */
    public static String execute(String description, TaskList tasks, Storage storage) {
        return tasks.printTaskList();
    }
}
