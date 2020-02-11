package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.exception.DukeException;

import duke.task.Task;

public class AddCommand extends Command {
    /** The task to add to a task list. */
    private Task task;

    /**
     * Constructs a new command that adds a task to a task list.
     *
     * @param task the task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Add task to the list
        TaskList newTasks = tasks.addTask(task);

        ui.showAdd(task);
        ui.showLineBreak(2);
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
