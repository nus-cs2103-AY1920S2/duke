package duke.command;

import duke.command.exception.SaveException;
import duke.command.exception.UnknownTaskIndexException;
import duke.storage.Storage;
import duke.storage.exception.StorageException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task from Duke.
 */
public class DeleteCommand extends Command {
    /** The id number of the task to delete. */
    private int taskId;

    /**
     * Constructs a {@code DeleteCommand} to deletes a {@code Task} from Duke.
     *
     * @param taskId the id number of the task to delete.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage)
            throws SaveException, UnknownTaskIndexException {
        // Invalid task number
        if (taskId <= 0 || taskId > tasks.getNumTasks()) {
            throw new UnknownTaskIndexException();
        }

        // Extract the deleted task
        Task deletedTask = tasks.get(taskId);
        TaskList newTasks = tasks.deleteTask(taskId);

        ui.showDelete(deletedTask);
        ui.showText("\n\n");
        ui.showTaskCount(newTasks);

        // Save immediately
        try {
            storage.save(newTasks);
        } catch (StorageException e) {
            throw new SaveException(e.getFilePath());
        }

        return newTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
