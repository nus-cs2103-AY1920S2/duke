package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Storage;
import processor.Ui;

/**
 * Parent class for the "todo", "deadline" and "event" commands.
 */
public class CommandTask implements Command {

    /**
     * Prints the task count to the UI and attempts to save the current list of instructions.
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     * @throws DukeException
     */
    public void execute(DukeProcessor processor, String args) throws DukeException {
        Ui.print("You've now got " + processor.getTaskList().size() + " tasks in your list.");

        try {
            Storage.saveTasks(processor);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
