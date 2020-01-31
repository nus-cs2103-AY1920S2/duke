package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Ui;

/**
 * Finds a task using the keyword entered.
 */
public class CommandFind implements Command {

    /**
     * Finds a task in the list using the keyword entered as input.
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     */
    public void execute(DukeProcessor processor, String args) {
        String[] argsArray = args.split(" ", 2);
        String searchString = argsArray[1];

        Ui.print(String.format("We've found the following tasks related to your search (\"%s\"):", searchString));
        processor.getTaskList().printTasksContaining(searchString);
    }
}
