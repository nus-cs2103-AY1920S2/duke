package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import tasks.DeadlineTask;

/**
 * Command that handles creating and adding a "deadline" task to the TaskList.
 */
public class CommandDeadline extends CommandTask {

    /**
     * Attempts to create a "deadline" task, and add it to the processor's TaskList.
     *
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     * @throws DukeException Throws an exception if the input format is incorrect.
     */
    public String execute(DukeProcessor processor, String args) throws DukeException {
        String[] inputArgs = args.split(" ", 2)[1].split(" /by ");

        DeadlineTask task = new DeadlineTask(inputArgs[0], inputArgs[1]);
        processor.getTaskList().add(task);

        String output = String.format("%s\n%s\n%s\n", "I've got it! Added the following task:",
                task.toString(),
                "You've now got " + processor.getTaskList().size() + " tasks in your list.");

        super.execute(processor, args);
        return output;
    }
}
