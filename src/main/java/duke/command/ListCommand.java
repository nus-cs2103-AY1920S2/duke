package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.interaction.Ui;
import duke.util.Storage;

import java.util.ArrayList;

/**
 * Represents the Command for the "list" input by the user.
 * It displays all task items in the task list.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class ListCommand extends Command {

    /**
     * Executes List behaviour of showing all tasks in collection.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        Ui.showAllTasks(taskList);
    }

    /**
     * Executes List behaviour of showing all tasks in collection
     * and returns the result as a String
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     * @return a String representing the output.
     */
    public String executeWithBotResponse(TaskList taskList, Storage storage) {
        String output = "";
        ArrayList<Task> listToShow = taskList.getList();

        if (!listToShow.isEmpty()) {
            for (int i = 1; i <= listToShow.size(); i++) {
                output += i + "." + listToShow.get(i - 1).toString() + "\n";
            }
        } else {
            output = "Empty List. You are currently free! Upz lah!";
        }

        return output;
    }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
