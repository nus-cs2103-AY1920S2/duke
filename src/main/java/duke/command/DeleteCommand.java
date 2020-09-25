package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TasksList;

/**
 * The handler for the delete command
 */
public class DeleteCommand extends Command {
    String description;

    /**
     * Constructs a DeleteCommand and sets the description keyword to look for
     *
     * @param description The string to search for when looking for what to delete
     */
    public DeleteCommand(String description) {
        this.description = description;
    }


    /**
     * Deletes the first instance of a task with a matching description to 'description'
     *
     * @param tasksList the tasksList to delete from
     * @param ui used to print succesful removal of a task from tasksList
     * @param storage for saving the updated tasks list to the save file
     *
     * @exception DukeException when there is a failed delete or was unable to save, this method
     * relays it.
     * */
    @Override
    public void execute(TasksList tasksList, Ui ui, Storage storage) throws DukeException {
        Task task = tasksList.delete(description);
        ui.printSuccessfulRemoveEntry(tasksList, task);
        storage.saveTasksList(tasksList);
    }
}
