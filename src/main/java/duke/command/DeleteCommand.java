package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;

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
     * @param storage storage object for the retrieval/saving of tasks.
     * @return The program's output.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            //delete the desired task
            Task taskToDelete = tasks.deleteTask(index);

            //update save file
            storage.saveTasks(tasks.getList());

            //print success message
            return String.format("Here I go! My ultimate destructive magic! EXPLOSION!\n"
                    + "I have successfully eradicated this task:\n    %s\n"
                    + "Now you have %d tasks in the list.\n", taskToDelete, tasks.getSize());
        } catch (InvalidCommandException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Sorry, I could not write to the magic saving item (Error when writing to save file).";
        }
    }
}
