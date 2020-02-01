package commands;

import dukeexception.SaveException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task t) {
        this.task = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException {
        tasks.addTask(this.task);
        storage.saveTasks(tasks.getTasks());
        String msg = "Got it! I've added this task!: \n";
        msg += "  " + this.task;
        msg += "\nNow you have " + tasks.getNumTasks() + " tasks in the list.";
        ui.printMsg(msg);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
