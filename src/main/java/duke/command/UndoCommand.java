package duke.command;

import duke.DukeException;
import duke.History;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents an undo command.
 */
public class UndoCommand extends Command {


    /**
     * Undo a command and returns the relevant messages.
     *
     * @param tasks The TaskList that contains list of tasks.
     * @param ui The Ui that deals with interactions with user.
     * @param storage The Storage deals with loading and saving tasks in file.
     * @param history The History that deals with past commands.
     * @return The relevant messages in the form of string.
     * @throws DukeException If there is no more history to undo to.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, History history) throws DukeException {
        history.removeHistory(tasks);
        return ui.generateUndoMessage(tasks.getTasks());
    }
}
