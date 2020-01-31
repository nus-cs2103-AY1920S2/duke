package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes task from the task list.
 */
public class DeleteCommand extends Command {

    protected int taskNo;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param taskNo The index to delete from the task list.
     */
    public DeleteCommand(int taskNo) {
        super(false);
        this.taskNo = taskNo;
    }

    /**
     * Executes the DeleteCommand by deleting the given task number from the task list.
     *
     * @param tasks   TaskList of Duke.
     * @param ui      The user interface.
     * @param storage To load from and save to the disk.
     * @throws DukeException thrown from deleteTask in the class TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.deleteTask(taskNo);
    }
}
