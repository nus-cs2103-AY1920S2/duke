package commands;

import dukeexception.SaveException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

/**
 * Adds a Task to the TaskList.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task task is the Task to be added into TaskList.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the AddCommand. Adds task into TaskList.
     *
     * @param tasks This is the TaskList new Task is being added to.
     * @param ui This is to interact with the user interface, printing message of Task being added.
     * @param storage This allows for TaskList to be updated with new Task.
     * @throws SaveException thrown when not able to save file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SaveException {
        tasks.addTask(this.task);
        storage.saveTasks(tasks.getTasks());
        String msg = "Got it! I've added this task!: \n";
        msg += "  " + this.task;
        msg += "\nNow you have " + tasks.getNumTasks() + " tasks in the list.";
        ui.printMsg(msg);
        return msg;
    }

    /**
     * AddCommand does not cause the programme to exit.
     *
     * @return boolean false since not ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
