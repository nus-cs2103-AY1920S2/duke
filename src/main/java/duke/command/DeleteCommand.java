package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

/*
 * DeleteCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * <p>DeleteCommand extends the command abstract class
 * and it describes the behavior of the commands regarding
 * to the deletion of a task.</p>
 * @author Mario Lorenzo
 */

public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs the DeleteCommand
     * @param index The index of the task.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @return The String representing the outcome of the execution.
     */

    public String execute(TaskList taskList, Storage storage) {
        return taskList.deleteTask(this.index, storage);
    }

    /**
     * Overrides the Object's equals method to compare
     * whether two DeleteCommand instances are equal.
     * @param object The object that wants to be compared.
     * @return The boolean whether it is equal or not.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof DeleteCommand) {
            DeleteCommand castedDeleteCommand = (DeleteCommand)object;
            return this.index == castedDeleteCommand.index;
        } else {
            return false;
        }
    }
}
