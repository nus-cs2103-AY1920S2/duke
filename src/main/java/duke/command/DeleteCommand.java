package duke.command;

import duke.exception.DukeException;
import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    protected int indexToDelete;

    /**
     * Constructor for the Delete Command.
     *
     * @param index The index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.indexToDelete = Math.max(0, index);
    }

    /**
     * Deletes the task in the input TaskList that matches the index that the user has requested to be deleted.
     *
     * @param taskList The TaskList to delete from.
     * @param ui The Ui used to print any notifications.
     * @throws DukeException If the index to delete is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        DukeException.throwIf(!taskList.isIndexValid(indexToDelete), "The input index is out of bounds!");
        Task deletedTask = taskList.removeAtIndex(indexToDelete);
        ui.stylizedPrint(
                "You have removed the following task:\n",
                "\t" + deletedTask.toString(),
                String.format("You now have %d task(s) in the list.", taskList.size()));
    }
}
