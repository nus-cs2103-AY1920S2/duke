package commands;

import processor.DukeProcessor;

/**
 * Command that prints out the list of tasks as entered by the user, in the order that they were entered.
 */
public class CommandList implements Command {

    /**
     * Prints the list of tasks.
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     */
    public void execute(DukeProcessor processor, String args) {
        processor.getTaskList().printTasks();
    }
}
