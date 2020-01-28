/**
 * Encapsulates a "bye" command from the user.
 * The "bye" command takes in no arguments, and any provided arguments will be ignored.
 */
public class ByeCommand implements Command {
    /**
     * Constructs a new ByeCommand instance.
     */
    public ByeCommand() {
    }
    
    /**
     * Prints the goodbye message in the UI.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }
    
    /**
     * Returns true, since "bye" is an exit command.
     * @return true
     */
    public boolean isExit() {
        return true;
    }
}
