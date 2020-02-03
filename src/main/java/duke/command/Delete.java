package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class Delete extends Command {

    private int taskNumber;

    public Delete(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        Task deletedTask = tasks.deleteTask(taskNumber);
        storage.save(tasks);
        return ui.showDeleteMessage(deletedTask, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
