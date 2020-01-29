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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;

        try {
            storage.write(tasks);
        } catch (IOException e) {
            ui.showError(e);
        }

        ui.goodBye();
    }
}
