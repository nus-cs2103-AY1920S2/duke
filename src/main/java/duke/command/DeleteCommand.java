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
public class DeleteCommand implements Command {
    private static final String SAVE_WRITE_ERROR_MESSAGE = "Sorry, I could not write to the magic saving item"
            + " (Error when writing to save file).\n";

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
            Task taskDeleted = tasks.deleteTask(index);
            assert taskDeleted != null : "There cannot be a null deleted task";
            storage.saveTasks(tasks.getList());
            ui.addMessage(String.format("Here I go! My ultimate destructive magic! EXPLOSION!\n"
                    + "I have successfully eradicated this task:\n  %s\n"
                    + "Now you have %d tasks in the list.\n", taskDeleted, tasks.getSize()));
        } catch (InvalidCommandException e) {
            ui.addMessage(e.getMessage());
        } catch (IOException e) {
            ui.addMessage(SAVE_WRITE_ERROR_MESSAGE);
        }
    }
}
