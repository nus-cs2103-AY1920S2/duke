package duke.command;

import duke.main.TaskList;

public class SortCommand {

    /**
     * Sorts the list of tasks by their alphabetical order.
     * @param tasks the list of tasks stored
     * @return the output to inform user that the tasks have been sorted
     */
    public static String run(TaskList tasks) {
        tasks.sortByAlphabeticalOrder();
        return "Noted, I've sorted all the tasks according to alphabetical order\n";
    }
}
