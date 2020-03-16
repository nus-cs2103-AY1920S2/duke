import java.io.IOException;

/**
 * The abstract Command class is used for extension to the different commands for different interaction
 * with the saved TaskList and output printed to the user.
 */
abstract class Command {
    protected String command;
    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;

    /**
     * This method is used to terminate the run() method in the Duke class if the command
     * is an exit command.
     */
    abstract boolean isExit();

    /**
     * This abstract method is inherited by all child classes of the Command class
     * for different interaction with the saved TaskList and updating
     * the duke.txt file.
     * @param tasks This is the saved TaskList in duke.txt.
     * @param ui This is the created Ui in Duke.
     * @param storage This is responsible for loading and saving the updated TaskList.
     * @throws IOException if file cannot be written to or closed.
     * @see IOException
     */
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
