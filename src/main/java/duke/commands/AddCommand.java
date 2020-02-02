package duke.commands;
import duke.utilities.*;
import duke.tasks.*;
import duke.Ui;

public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean execute (Storage storage, TaskList taskList, Ui ui) {
        taskList.addTask(task);
        storage.update(taskList.getTaskList());
        ui.taskMsg(task, taskList.getListSize());
        return true;
    }
}
