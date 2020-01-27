package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    protected int taskNumber;

    public DoneCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        ui.markTaskAsDone(task);
    }
}
