package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class Command {
    protected String taskString;

    public Command() {
    }

    public Command(String taskString) {
        this.taskString = taskString;
    }

    /**
     * Executes the current command.
     *
     * @param tasks TaskList instance of Duke program.
     * @param ui UI instance of Duke program.
     * @param storage Storage instance of Duke program.
     * @throws DukeException DukeException thrown when command cannot be executed successfully.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Check if the command is an exit command.
     *
     * @return A boolean if the command is an exit command.
     */
    public abstract boolean isExit();
}