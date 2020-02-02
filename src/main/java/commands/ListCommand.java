package commands;

import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

/**
 * List out all Tasks in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Execute the ListCommand. It marks Task as done.
     *
     * @param tasks This is the TaskList where the Task is stored.
     * @param ui This is to interact with the user interface, printing all Tasks.
     * @param storage Unused.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.getNumTasks(); i++) {
            Task curTask = tasks.getTasks().get(i);
            msg += "\n" + (i + 1) + ". " + curTask;
        }
        ui.printMsg(msg);
        return msg;
    }

    /**
     * List Command does not cause the programme to exit.
     *
     * @return boolean false since not ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
