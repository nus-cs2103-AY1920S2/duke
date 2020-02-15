package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.exception.DukeException;

import duke.task.Task;

public class DeleteCommand extends Command {
    /** The id number of the task to delete from a task list. */
    private int taskId;

    /**
     * Constructs a new command that deletes a task from a task list.
     *
     * @param taskId the id number of the task to delete from a list.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Invalid task number
        if (taskId <= 0 || taskId > tasks.getNumTasks()) {
            throw new DukeException("Your task number does not exist.");
        }

        // Extract the deleted task
        Task deletedTask = tasks.get(taskId);
        TaskList newTasks = tasks.deleteTask(taskId);

        ui.showDelete(deletedTask);
        ui.showText("\n\n");
        ui.showTaskCount(newTasks);

        // Save immediately
        storage.save(newTasks);

        return newTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
