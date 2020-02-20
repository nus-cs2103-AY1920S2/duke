package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidIndexException;
import duke.gui.Gui;
import duke.task.Task;

public class UpdateTodoCommand extends Command {
    protected int index;
    protected String description;

    public UpdateTodoCommand(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public Task getTask(TaskList taskList) {
        try {
            return taskList.getTaskList().get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(Integer.toString(index + 1));
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = getTask(taskList);
        task.setDescription(description);
        ui.showUpdate(task, taskList.getTaskList());
    }

    @Override
    public String execute(TaskList taskList, Gui gui) {
        Task task = getTask(taskList);
        task.setDescription(description);
        return gui.showUpdate(task, taskList.getTaskList());
    }
}
