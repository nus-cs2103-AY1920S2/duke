package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Saves task list into disk.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the ExitCommand by saving the current task list into disk, then print the exit message.
     *
     * @param tasks   TaskList of Duke.
     * @param storage To load from and save to the disk.
     * @return Exit message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        storage.writeToFile(tasks.getTasks());
        return "     Bye. Hope to see you again soon!";
    }
}
