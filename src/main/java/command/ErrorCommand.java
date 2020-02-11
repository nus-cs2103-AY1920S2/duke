package command;

import duke.Storage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * A command object for displaying an error.
 */
public class ErrorCommand extends Command {
    private final DukeException de;

    /**
     * Constructs a command object to display an error.
     * @param de The exception to be displayed.
     */
    public ErrorCommand(DukeException de) {
        this.de = de;
    }

    /**
     * Executes the command to change task's status in the list.
     * @param tasks List of tasks of the Duke object.
     * @param ui UI object of the Duke object.
     * @param storage Storage object of the Duke object.
     * @return String as the response of the execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return Ui.showException(de);
    }
}
