package duke.commands;

import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class ExitCommand extends Command {

    /**
     * Exits the program upon user input of "bye"
     * @param description Description of task
     * @param tasks List of current tasks
     * @param storage For storing of tasks into file
     * @throws DukeException If input format is wrong. Not used here
     */
    public static String execute(String description, TaskList tasks, Storage storage) {
        return "See you again!";
    }
}
