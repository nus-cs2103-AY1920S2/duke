package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class AddTaskCommand extends Command {
    protected Task task;

    public AddTaskCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.printTaskAddition(task, tasks.size());
        storage.updateSaveFile(tasks);
    }
}
