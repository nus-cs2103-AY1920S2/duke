package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

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
     * @param storage To load from and save to the disk.
     * @return Acknowledgement message sent by Duke.
     * @throws DukeException thrown from deleteTask in the class TaskList.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return tasks.deleteTask(taskNo);
    }
}
