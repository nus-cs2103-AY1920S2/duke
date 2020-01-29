package duke.command;

import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;

/*
 * AddCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * <p>AddCommand extends the Command abstract class
 * and it describes the behavior of a command regarding
 * to adding task.</p>
 * @author Mario Lorenzo
 */


public class AddCommand extends Command {
    Task task;

    /**
     * Constructs the AddCommand.
     * @param task The task that wants to be added.
     */

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to taskList
     * and write the details in the hard disk.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @return The String representing the outcome of the execution.
     */

    public String execute(TaskList taskList, Storage storage) {
        return taskList.addTask(task, storage);
    }

}
