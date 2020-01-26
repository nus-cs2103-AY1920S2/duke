package commands;

import exceptions.DukeException;
import processor.DukeProcessor;
import processor.Ui;
import tasks.DeadlineTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Command that handles creating and adding a "deadline" task to the TaskList.
 */
public class CommandDeadline extends CommandTask {

    /**
     * Attempts to create a "deadline" task, and add it to the processor's TaskList.
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     * @throws DukeException
     */
    public void execute(DukeProcessor processor, String args) throws DukeException {
        String[] inputArgs = args.split(" ", 2)[1].split(" /by ");
        if(!args.contains(" /by ") || inputArgs.length < 2) {
            throw new DukeException("Your deadline command is incorrect! Please follow the format: deadline <item> " +
                    "/by <time>");
        }

        DeadlineTask task = new DeadlineTask(inputArgs[0], inputArgs[1]);
        processor.getTaskList().add(task);

        Ui.print("I've got it! Added the following task:");
        Ui.print(task.toString());

        super.execute(processor, args);
    }
}
