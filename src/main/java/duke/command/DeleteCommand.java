package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a DeleteCommand.
 * Used to execute the delete command.
 */
public class DeleteCommand extends Command {
    /** Index of the task to be deleted. */
    private int index = 0;

    /**
     * Constructs a new DeleteCommand.
     *
     * @param index index of the task in the task list to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            //delete the desired task
            Task taskToDelete = tasks.deleteTask(index);

            //print success message
            ui.printMessage(String.format("     Noted. I've removed this task:\n         %s\n"
                    + "     Now you have %d tasks in the list.\n", taskToDelete, tasks.getSize()));

            //update save file
            storage.saveTasks(tasks.getList());
        } catch (InvalidCommandException e) {
            ui.printException(e);
        } catch (IOException e) {
            ui.printMessage("     Sorry, I could not write to the save file.");
        }
    }
}
