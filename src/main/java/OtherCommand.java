/**
 * Represents a Command object that will be created when the user
 * inputs a command that is not recognised by Duke
 */
public class OtherCommand extends Command {
    public OtherCommand(String input) {
        super(input);

    }

    /**
     * Executes the command to flag to the user that the input is not
     * of a recognised format.
     * @param tasks TaskList object from the driver Duke object.
     * @param ui Ui object from the driver Duke object.
     * @param storage Storage object from the driver Duke object
     * @return String to be displayed back to the user
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.flagWrongCommand();
    }

}
