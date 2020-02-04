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
        // Check if task number is valid
        if (0 < taskId && taskId <= tasks.getNumTasks()) {
            // Extract the deleted task
            Task deletedTask = tasks.get(taskId);
            TaskList newList = tasks.deleteTask(taskId);

            ui.showDelete(deletedTask);
            ui.showLineBreak(2);
            ui.showTaskCount(newList);

            // Save immediately
            storage.save(newList);

            return newList;

        } else {
            // Invalid task number
            throw new DukeException("Your task number does not exist.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
