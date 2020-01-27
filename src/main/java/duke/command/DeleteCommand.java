package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    protected int taskNumber;

    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Task number given starts from 1
        Task removedTask = tasks.remove(taskNumber - 1);
        ui.printTaskDeletion(removedTask, tasks.size());
        storage.updateSaveFile(tasks);
    }
}
