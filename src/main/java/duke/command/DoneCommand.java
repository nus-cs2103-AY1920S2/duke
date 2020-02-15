package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

import duke.exception.DukeException;

public class DoneCommand extends Command {
    /** The id number of the task to mark as done in a list. */
    private int taskId;

    /**
     * Constructs a new command that marks a task as done in a task list.
     *
     * @param taskId the id number of the task to mark as done in a list.
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Invalid task number
        if (taskId <= 0 || taskId > tasks.getNumTasks()) {
            throw new DukeException("Your task number does not exist.");
        }

        // Mark task as done
        TaskList newTasks = tasks.doneTask(taskId);

        ui.showDone(newTasks.get(taskId));

        // Save immediately
        storage.save(newTasks);

        return newTasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
