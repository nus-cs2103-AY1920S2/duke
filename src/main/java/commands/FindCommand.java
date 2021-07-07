package commands;

import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

/**
 * Adds a Task to the TaskList.
 */
public class FindCommand extends Command {

    private String str;

    /**
     * Constructor for FindCommand.
     *
     * @param str This is the String of keyword used to find Task.
     */
    public FindCommand(String str) {
        this.str = str;
    }

    /**
     * Execute the FindCommand. Find tasks with user input keyword.
     *
     * @param tasks This is the TaskList where the Task is stored.
     * @param ui This is to interact with the user interface, printing Tasks with keyword.
     * @param storage Unused.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Here are the matching tasks in your list:";
        int counter = 0;
        for (Task t : tasks.getTasks()) {
            if (t.getDescription().contains(this.str)) {
                counter++;
                msg += "\n" + counter + ". " + t;
            }
        }
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
