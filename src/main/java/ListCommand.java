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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listRecord();
    }
}
