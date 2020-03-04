package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import tasks.Task;

import java.util.List;

/**
 * Finds a task using the keyword entered.
 */
public class CommandFind implements Command {

    /**
     * Finds a task in the list using the keyword entered as input.
     *
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     */
    public String execute(DukeProcessor processor, String args) throws DukeException {
        String[] argsArray = args.split(" ", 2);
        String searchString = argsArray[1];

        List<Task> tasksList = processor.getTaskList().getTasksContaining(searchString);
        String tasksListString = "";
        for (Task task : tasksList) {
            tasksListString += task.toString() + "\n";
        }

        String output = String.format("%s\n%s\n", String.format("We've found the following tasks related to your "
                        + "search (\"%s\"):", searchString),
                tasksListString);

        return output;
    }
}
