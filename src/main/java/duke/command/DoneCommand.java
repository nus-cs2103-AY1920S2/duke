package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

/*
 * DoneCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * <p>DoneCommand extends the command abstract class
 * and it describes the behavior of the commands regarding
 * to the marking tasks as done.</p>
 * @author Mario Lorenzo
 */

public class DoneCommand extends Command {
    private int index;

    /**
     * Constructs a DoneCommand instance.
     * @param index The index of a task that wants to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the done command.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @return The String representing the outcome of the execution.
     */

    public String execute(TaskList taskList, Storage storage) {
        return taskList.markDone(this.index, storage);
    }
}
