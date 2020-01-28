package duke.command;

import duke.util.Storage;
import duke.util.TaskList;

/*
 * ListCommand
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * <p>ListCommand extends the command abstract class
 * and it describes the behavior of the commands regarding
 * to list the tasks.</p>
 * @author Mario Lorenzo
 */

public class ListCommand extends Command {

    /**
     * Constructs a ListCommand instance.
     */

    public ListCommand() {

    }

    /**
     * Executes the list command.
     * @param taskList The list of tasks.
     * @param storage The writer to the hard disk.
     * @return The String representing the outcome of the execution.
     */

    public String execute(TaskList taskList, Storage storage) {
        return taskList.listTasks();
    }
}
