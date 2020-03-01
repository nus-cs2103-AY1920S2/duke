package duke.command;

import duke.command.exception.DuplicateTaskException;
import duke.command.exception.SaveException;

import duke.storage.exception.StorageException;
import duke.storage.Storage;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

/**
 * Adds a task into Duke.
 */
public class AddCommand extends Command {
    /** The task to add. */
    private Task task;

    /**
     * Constructs an {@code AddCommand} to add a {@code Task} into Duke.
     *
     * @param task the task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage)
            throws SaveException, DuplicateTaskException {

        // Check if the task is a duplicate
        if (tasks.containsTask(task)) {
            throw new DuplicateTaskException();
        }

        // Add task to the list
        TaskList newTasks = tasks.addTask(task);

        ui.showAdd(task);
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
