package duke.command;

import duke.gui.Gui;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidIndexException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Deletes task from list of tasks.
     * @param taskList List of tasks.
     * @return The task deleted
     */
    public Task delete(TaskList taskList) {
        try {
            Task task = taskList.getTaskList().get(index);
            taskList.getTaskList().remove(task);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(Integer.toString(index + 1));
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = delete(taskList);
        ui.showDelete(task, taskList.getTaskList());
    }

    @Override
    public String execute(TaskList taskList, Gui gui) {
        Task task = delete(taskList);
        return gui.showDelete(task, taskList.getTaskList());
    }
}
