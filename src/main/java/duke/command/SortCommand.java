package duke.command;

import duke.main.TaskList;

public class SortCommand {

    public static String run(TaskList tasks) {
        tasks.sortByAlphabeticalOrder();
        return "Noted, I've sorted all the tasks according to alphabetical order\n";
    }
}
