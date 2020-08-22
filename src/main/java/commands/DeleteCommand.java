package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private int deleteIndex;

    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.getTask(deleteIndex - 1);
        taskList.deleteTask(deleteIndex - 1);
        storage.overwrite(taskList.getTasks());
        return ui.showDeleteMessage(task, deleteIndex, taskList.getSize());
    }
}
