package duke.commands;

import duke.exceptions.DukeException;
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
     * @return String add message
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        taskList.addTask(task);
        storage.update(taskList.getTaskList());
        return ui.taskMsg(task, taskList.getListSize());
    }
}
