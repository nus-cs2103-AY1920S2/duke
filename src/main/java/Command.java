/**
 * Abstract class representing a command to be executed later.
 */
public abstract class Command {
    /**
     * Describes whether the command is an exit command.
     */
    protected boolean isExit = false;

    /**
     * Does a series of steps to fulfill the command.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface
     * @param storage the storage for the task list
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return isExit;
    }
}
