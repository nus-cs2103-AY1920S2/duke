package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

/*
 * Command
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * <p>Command abstract class defines behavior of commands
 * entered by client. A command have an execute method.</p>
 * @author Mario Lorenzo
 */

public abstract class Command {
    /**
     * Executes the command.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @return The String representing the outcome of the execution.
     */
    public abstract String execute(TaskList taskList, Storage storage);
}
