package duke.command;

import duke.interaction.Ui;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents the Command for the "delete" input by the user.
 * It deletes the task at the input task index from the task list.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class DeleteCommand extends Command {

    int indexToDelete;

    /**
     * DeleteCommand constructor.
     * @param index of the task in collection to be deleted.
     */
    public DeleteCommand(int index) {
        indexToDelete = index;
    }

    /**
     * Executes Delete behaviour of deleted task at given index.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        String output = taskList.deleteTask(indexToDelete);

        if (output != null) {
            storage.saveTaskListToFile(taskList);
            Ui.showTaskDelete(output, taskList.getList().size());
        } else {
            Ui.showTaskNotFound();
        }
    }

    /**
     * Executes Delete behaviour of deleted task at given index
     * and returns the result as a String
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     * @return a String representing the output.
     */
    public String executeWithBotResponse(TaskList taskList, Storage storage) {
        String output = "";
        String taskToRemove = taskList.deleteTask(indexToDelete);

        if (taskToRemove != null) {
            storage.saveTaskListToFile(taskList);
            int total = taskList.getList().size();

            output = "Noted! I've removed this task:\n"
                    + taskToRemove + "\n"
                    + "Now you have " + total
                    + " task" + (total != 1 ? "s" : "") + " in the list.";
        } else {
            output = "Sorry, mate! No such task.";
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
