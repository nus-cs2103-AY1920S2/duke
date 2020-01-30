package ip.command;

import ip.Ui;
import ip.task.Task;
import ip.task.TaskList;

public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task){
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.add(task);
        ui.displayTaskAdded(task, tasks.size());
    }
}
