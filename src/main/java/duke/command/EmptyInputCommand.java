package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/** Represents a command that has empty user input. */
public class EmptyInputCommand extends Command {

    public EmptyInputCommand() {
        super(false);
    }

    /**
     * Displays command not found message to user.
     *
     * @param tasks list of tasks
     * @param ui prints output to user
     * @param storage manages save file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.commandNotFound();
    }
}
