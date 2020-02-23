package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

/**
 * Represents the Command used to delete a new Task.
 */
public class DeleteCommand extends Command {
    private int deletedTaskNumber;

    public DeleteCommand(int deletedTaskNumber) {
        this.deletedTaskNumber = deletedTaskNumber;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        Task deleted;
        try {
            deleted = taskList.deleteTask(deletedTaskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no such task with the number you have specified. "
                    + "Kindly check list again.");
        }
        return ui.showDeleteMessage(deleted);
    }
}