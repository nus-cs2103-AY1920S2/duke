/**
 * Represents a find command that finds items from list. A <code>FindCommand</code> object
 * corresponds to a find command represented by the command and a description e.g.,
 * <code>"find", "read"</code>
 */
public class FindCommand extends Command {

    /**
     * A constructor for a find command object.
     * @param command command called
     * @param description description of command
     */
    public FindCommand(String command, String description) {
        super(command, description);
    }

    /**
     * Generates a string of tasks containing keyword.
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     * @return A string containing the output
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.find(description);

        return output;
    }

    @Override
    public String undo() {
        return "Nothing to undo";
    }
}
