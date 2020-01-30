package duke.command;

import duke.interaction.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

import java.util.ArrayList;

/**
 * Represents the Command for the "add" input by the user.
 * It adds a new task to the task list.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class AddCommand extends Command {

    private String toAdd;
    private Task.TaskType taskType;

    /**
     * AddCommand constructor.
     * @param toAdd string name of new task.
     * @param taskType of the new task.
     */
    public AddCommand(String toAdd, Task.TaskType taskType) {
        this.toAdd = toAdd;
        this.taskType = taskType;
    }

    /**
     * Executes Add behaviour of adding a new task.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        String output = null;
        try {
            output = taskList.addTask(toAdd, taskType, storage);
            Ui.showTaskAdded(output, taskList.getList().size());
        } catch (Exception e) {
            Ui.showError(e);
        }
    }

    /**
     * Executes Add behaviour of adding a new task
     * and returns the result as a String
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     * @return a String representing the output.
     */
    public String executeWithBotResponse(TaskList taskList, Storage storage) {
        String output = "";
        String addedTask = null;

        try {
            addedTask = taskList.addTask(toAdd, taskType, storage);
            int total = taskList.getList().size();
            output = "Got it. I've added this task:\n"
                    + addedTask + "\n"
                    + "Now you have " + total + " task"
                    + (total != 1 ? "s" : "") + " in the list.";
        } catch (Exception e) {
            output = e.getMessage();
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
