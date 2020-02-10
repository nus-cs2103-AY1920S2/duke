package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class Add extends Command {

    public Add(Task task) {
        super(task);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(super.getTask());
        storage.save(tasks);
        return ui.showCustomiseMessage(super.getTask().toString(), tasks.getSize());

    }
}
