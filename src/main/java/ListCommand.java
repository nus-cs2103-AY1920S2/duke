/**
 * Represents a list command that list the items in the list. A <code>ListCommand</code> object
 * corresponds to a list command represented by the command and a description e.g.,
 * <code>"list", ""</code>
 */
public class ListCommand extends Command {

    /**
     * Constructor for list command object.
     * @param command list command
     * @param description empty string
     */
    public ListCommand(String command, String description) {
        super(command, description);
    }

    /**
     * Generates a string containing the list of tasks.
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     * @return A string containing the list of tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        return tasks.listRecord();
    }

    @Override
    public String undo() {
        return "Nothing to undo";
    }
}
