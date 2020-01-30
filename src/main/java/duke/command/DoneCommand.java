package duke.command;

import duke.interaction.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

import java.util.ArrayList;

/**
 * Represents the Command for the "done" input by the user.
 * It sets the input task index "isDone" status to true.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class DoneCommand extends Command {

    int doneIndex;

    /**
     * DoneCommand constructor.
     * @param index of the task in collection to be set Done.
     */
    public DoneCommand(int index) {
        doneIndex = index;
    }

    /**
     * Executes Done behaviour of setting given task to Done.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        if (taskList.doneTask(doneIndex)) {
            storage.saveTaskListToFile(taskList);
            Ui.showTaskDone(taskList.getList().get(doneIndex));
        } else {
            Ui.showTaskNotFound();
        }
    }

    /**
     * Executes Done behaviour of setting given task to Done
     * and returns the result as a String
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     * @return a String representing the output.
     */
    public String executeWithBotResponse(TaskList taskList, Storage storage) {
        String output = "";

        if (taskList.doneTask(doneIndex)) {
            storage.saveTaskListToFile(taskList);
            output = "Nice! I've marked this task as done:\n"
                    + taskList.getList().get(doneIndex).toString();
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
