import java.io.IOException;

/**
 * Represents a deadline task. A <code>Deadline/code> object
 * corresponds to a command represented by the command and a description e.g.,
 * <code>"delete", "2"</code>
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String command, String description) {
        super(command, description);
    }

    /**
     * Executes delete command.
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int num = Integer.parseInt(description);

        tasks.delete(num);

        try {
            storage.write(tasks);
        } catch (IOException e) {
            ui.showError(e);
        }
    }
}
