package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;

import java.util.ArrayList;

/**
 * Represents the Command for the "bye" input by the user.
 * It does not have any <code>execute</code> behaviour but the
 * <code>isExit</code> informs the main loop to end the program.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class ExitCommand extends Command {

    public void execute(TaskList taskList, Storage storage) {

    }

    /**
     * Represents the Command for the "bye" input by the user
     * and returns the result as a String
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     * @return a String representing the output.
     */
    public String executeWithBotResponse(TaskList taskList, Storage storage) {
        // TODO: further finalize the "bye" behaviour
        return "";
    }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return true;
    }
}
