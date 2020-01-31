/**
 * Command class.
 */
public abstract class Command {
    private String inputCommand; //input command by the user
    private boolean isExit;

    /**
     * Constructor.
     * @param inputCommand command inputted by user.
     * @param isExit whether to exit the chat bot or not.
     */
    public Command(String inputCommand, boolean isExit) {
        this.inputCommand = inputCommand;
        this.isExit = isExit;
    }

    /**
     * Return the command inputted by user.
     * @return
     */
    public String getInputCommand() {
        return this.inputCommand;
    }

    /**
     * Execute the given task.
     * @param ui the Ui class in charge of aesthetics.
     * @param list tasklist that stores all the task.
     * @param storage storage that writes to file.
     * @throws DukeException exception.
     */
    public abstract void execute(UI ui, TaskList list, Storage storage) throws DukeException;

    /**
     * Return whether to exit or not.
     * @return boolean, true exit, false means continue.
     */
    public boolean getExitStatus() {
        return this.isExit;
    }
}
