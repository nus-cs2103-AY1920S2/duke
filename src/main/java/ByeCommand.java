/**
 * Represents a Command object that will be created when the user inputs an input to flag the end of his/her input.
 */
public class ByeCommand extends Command {
    public ByeCommand(String cmd) {
        super(cmd);
    }

    /**
     * Sets the boolean value of isExit the current Command object to true.
     * @param tasks TaskList object from the driver Duke object
     * @param ui Ui object from the driver Duke object
     * @param storage Storage object from the driver Duke object
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.setExit();
        return ui.sayBye();
    }
}
