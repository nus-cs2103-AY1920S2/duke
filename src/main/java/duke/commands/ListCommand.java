package duke.commands;

import duke.tasks.TaskList;
import duke.exceptions.DukeException;

public class ListCommand implements Command {

    /**
     * Prints out the current list of tasks
     * @param tasks List of current tasks
     * @throws DukeException If input format is wrong. Not used here
     */
    public static String execute(TaskList tasks) throws DukeException {
        StringBuilder output = new StringBuilder();

        output.append("This is what you've asked me to track!\n");
        output.append(tasks.printTaskList());
        return output.toString();
    }
}
