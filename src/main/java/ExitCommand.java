import java.io.IOException;

/**
 * Represents an exit command that terminates bot. A <code>ExitCommand</code> object
 * corresponds to an exit command represented by the command and a description e.g.,
 * <code>"exit", ""</code>
 */
public class ExitCommand extends Command {

    /**
     * Constructor for exit command object.
     * @param command exit command
     * @param description empty string
     */
    public ExitCommand(String command, String description) {
        super(command, description);
    }

    /**
     * Writes list into text file and says goodbye to user.
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     * @returnA A string containing the output
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.write(tasks);
        } catch (IOException e) {
            return ui.showError(e);
        }

        return ui.goodBye();
    }

    @Override
    public String undo() {
        return "Nothing to undo";
    }
}
