/**
 * Represents a command from the user. A <code>Command</code> object
 * corresponds to a command represented by the command and a description e.g.,
 * <code>"deadline", "read /by 2019-05-10 1800"</code>
 */
public abstract class Command {

    public String command;
    public String description;
    public boolean isExit;

    /**
     * A constructor for a Command object.
     * @param command command called
     * @param description description of command
     */
    public Command(String command, String description) {
        this.command = command;
        this.description = description;
        isExit = false;
    }

    /**
     * An abstract method for sub-classes to implement actions based on command type.
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     * @throws DukeException throws a DukeException if description is not added
     */
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
