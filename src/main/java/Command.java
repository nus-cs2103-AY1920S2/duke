import java.io.IOException;

/**
 * The abstract Command class is used for extension to the different commands for different interaction
 * with the saved TaskList and output printed to the user.
 */
public abstract class Command {
    protected String command;
    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;

    /**
     * This method is used to terminate the run() method in the Duke class.
     *
     * @return This returns true if the command is an ExitCommand and false otherwise.
     */
    boolean isExit() {
        if (this instanceof ExitCommand) {
            return true;
        }
        return false;
    }

    /**
     * This abstract method is inherited by all child classes of the Command class for different interaction with
     * the saved TaskList and updating the duke.txt file.
     * @param tasks This is the saved TaskList in duke.txt.
     * @param ui This is the created Ui in Duke.
     * @param storage This is responsible for loading and saving the updated TaskList.
     * @throws IOException On input error.
     * @see IOException
     */
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
