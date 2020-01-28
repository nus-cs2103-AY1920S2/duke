package duke.command;

import duke.Storage;
import duke.TaskList;
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
        // Check if task number is valid
        if (0 < taskId && taskId <= tasks.getNumTasks()) {
            // Mark task as done
            TaskList newList = tasks.doneTask(taskId);

            ui.showDone(newList.get(taskId));

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
