package hakunamatata.command;

import hakunamatata.HakunaMatataException;
import hakunamatata.Storage;
import hakunamatata.Ui;
import hakunamatata.task.TaskList;

/**
 * Command is the abstract base class for all executable commands,
 * which handle the execution of commands and program termination.
 *
 * @author Wang Yuting
 */
public abstract class Command {

    /**
     * Executes command.
     *
     * @param tasks   The list of current tasks from storage.
     * @param ui      The current UI.
     * @param storage The system storage, used as database.
     * @throws HakunaMatataException If there is any HakunaMatataException.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws HakunaMatataException;

    /**
     * Returns true if it is an exit command.
     *
     * @return Returns true if it is an exit command.
     */
    public abstract boolean isExit();

}