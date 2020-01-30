package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

/**
 * Represents the a generic Command that will be extended from.
 * The method <code>execute</code> should be implemented and
 * called to run any command's behaviours.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public abstract class Command {

    /**
     * Abstract execute method for all Command subclasses.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public abstract void execute(TaskList taskList, Storage storage);

    /**
     * Abstract execute method for all Command subclasses to be used by the GUI
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     * @return a String representing the output.
     */
    public abstract String executeWithBotResponse(TaskList taskList, Storage storage);

    /**
     * Abstract method for all Command subclasses.
     * @return boolean indicating if command is an exit command.
     */
    public abstract boolean isExit();
}
