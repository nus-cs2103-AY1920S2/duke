package duke.command;

import duke.exception.DukeException;
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
        this.indexToDelete = index;
    }

    /**
     * Deletes the task in the input TaskList that matches the index that the user has requested to be deleted.
     *
     * @param taskList The TaskList to delete from.
     * @throws DukeException If the index to delete is invalid.
     */
    @Override
    public void execute(TaskList taskList) throws DukeException {
        DukeException.throwIf(!taskList.isIndexValid(indexToDelete), "The input index is out of bounds!");
        Task deletedTask = taskList.removeAtIndex(indexToDelete);
        System.out.println("You have removed the following task:\n" + deletedTask.toString());
        System.out.println(String.format("You now have %d task(s) in the list.", taskList.size()));
    }
}
