public abstract class Command {

    /**
     * Returns an updated task list after performing a specific command.
     * The actions that can be performed include task processing,
     * console outputting, and file I/O.
     *
     * @param tasks a list of tasks to update.
     * @param ui a user interface for console outputting.
     * @param storage a save/loading mechanism.
     * @return an updated task list after performing a specific command.
     * @throws DukeException if command encounters an issue when executing.
     */
    public abstract TaskList execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the command will exit the program.
     *
     * @return true if the command will exit the program, false otherwise.
     */
    public abstract boolean isExit();
}