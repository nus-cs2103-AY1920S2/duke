package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.util.Optional;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    /**
     * Updates task list in save file.
     *
     * @param tasks   list of tasks
     * @param ui      prints information to user
     * @param storage manages user save file
     * @return TaskList required for indicating updating of tasks
     */
    @Override
    public Optional<TaskList> execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
        updateSaveFile(storage, ui, tasks);
        return Optional.empty();
    }
}
