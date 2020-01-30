/**
 * An abstraction of the user input.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks Handler of tasks in Duke.
     * @param ui Handler of interactions with user.
     * @param storage Handler of tasks stored in file.
     * @throws DukeException If error occurs during execution of command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the command requires exiting Duke.
     * @return true if command requires exit, false if not.
     */
    public abstract boolean isExit();
}
