package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tags.TagList;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Abstract Command class.
 */
public abstract class Command {
    protected String taskString;

    /**
     * Command abstract constructor.
     */
    public Command() {
    }

    /**
     * Command constructor.
     *
     * @param taskString Descriptionn of the task
     */
    public Command(String taskString) {
        this.taskString = taskString;
    }

    /**
     * Executes the current command.
     *
     * @param tasks TaskList instance of Duke program.
     * @param tagList TagList instance of the Duke program.
     * @param ui UI instance of Duke program.
     * @param storage Storage instance of Duke program.
     * @throws DukeException DukeException thrown when command cannot be executed successfully.
     */
    public abstract String execute(TaskList tasks, TagList tagList, Ui ui, Storage storage) throws DukeException;

    /**
     * Check if the command is an exit command.
     *
     * @return A boolean if the command is an exit command.
     */
    public abstract boolean isExit();
}