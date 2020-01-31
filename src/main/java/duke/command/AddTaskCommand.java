package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/** Adds given task as a command. */
public class AddTaskCommand extends Command {
    protected Task task;

    public AddTaskCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Adds task given to list, print task information and update save file.
     *
     * @param tasks list of tasks
     * @param ui used to display information to user
     * @param storage to update save file when the task list is changed
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.printTaskAddition(task, tasks.size());
        storage.updateSaveFile(tasks);
    }
}
