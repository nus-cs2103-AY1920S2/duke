package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents the Command for the "bye" input by the user.
 * It does not have any <code>execute</code> behaviour but the
 * <code>isExit</code> informs the main loop to end the program.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class ExitCommand extends Command {

    public void execute(TaskList taskList, Storage storage) { }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return true;
    }
}
