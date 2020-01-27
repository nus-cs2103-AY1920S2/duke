package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/** Represents a command to list out all tasks in task list */
public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    /**
     * Prints out list of tasks.
     *
     * @param tasks list of tasks
     * @param ui prints information to user
     * @param storage manages user save file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Print out all tasks
        ui.listTasks(tasks);
    }
}
