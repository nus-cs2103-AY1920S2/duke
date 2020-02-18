package duke.command;

import duke.History;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;


/**
 * Represents an undo command.
 */
public class UndoCommand extends Command {


    /**
     * Undo a command and returns the relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param history The History that deals with past commands.
     * @return The relevant messages in the form of string.
     * @throws DukeException If there is no more history to undo to.
     */
    @Override
    public String execute(TaskList tasks, History history) throws DukeException {
        history.removeHistory(tasks);
        return Ui.generateUndoMessage(tasks.getTasks());
    }
}
