package duke.command;

import duke.command.exception.SaveException;
import duke.command.exception.UnknownTaskIndexException;
import duke.storage.Storage;
import duke.storage.exception.StorageException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks a task in Duke as done.
 */
public class DoneCommand extends Command {
    /** The id number of the task to mark as done. */
    private int taskId;

    /**
     * Constructs a new {@code DoneCommand} that marks a task as done in Duke.
     *
     * @param taskId the id number of the task to mark as done.
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage)
            throws SaveException, UnknownTaskIndexException {
        // Invalid task number
        if (taskId <= 0 || taskId > tasks.getNumTasks()) {
            throw new UnknownTaskIndexException();
        }

        // Mark task as done
        TaskList newTasks = tasks.doneTask(taskId);

        ui.showDone(newTasks.get(taskId));

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
