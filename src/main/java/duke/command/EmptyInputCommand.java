package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.util.Optional;

/**
 * Represents a command that has empty user input.
 */
public class EmptyInputCommand extends Command {

    public EmptyInputCommand() {
        super(false);
    }

    /**
     * Displays command not found message to user.
     *
     * @param tasks   list of tasks
     * @param ui      prints output to user
     * @param storage manages save file
     * @return TaskList required for indicating updating of tasks
     */
    @Override
    public Optional<TaskList> execute(TaskList tasks, Ui ui, Storage storage) {
        ui.commandNotFound();
        return Optional.empty();
    }
}
