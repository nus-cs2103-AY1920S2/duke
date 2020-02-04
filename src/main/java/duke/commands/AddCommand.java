package duke.commands;

import duke.utilities.*;
import duke.tasks.*;
import duke.Ui;

public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to TaskList object.
     * Updates tasks.txt file.
     * Prints Ui message for adding tasks
     *
     * @param storage
     * @param taskList
     * @param ui
     * @return true to indicate that this is not an ExitCommand
     */
    @Override
    public boolean execute(Storage storage, TaskList taskList, Ui ui) {
        taskList.addTask(task);
        storage.update(taskList.getTaskList());
        ui.taskMsg(task, taskList.getListSize());
        return true;
    }
}
