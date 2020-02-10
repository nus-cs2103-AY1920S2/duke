package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.util.Optional;

/**
 * Represents a command to list out all tasks in task list.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    /**
     * Prints out list of tasks.
     *
     * @param tasks   list of tasks
     * @param ui      prints information to user
     * @param storage manages user save file
     * @return TaskList required for indicating updating of tasks
     */
    @Override
    public Optional<TaskList> execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks, "Here are the tasks in your list:");
        return Optional.empty();
    }
}
