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
     * Executes delete command, updates storage and returns output to user.
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     * @return A string containing the output
     * @throws DukeException throws a DukeException if description is not added
     *
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("delete");
        } else {
            int num = Integer.parseInt(description);

            String output = tasks.delete(num);

            try {
                storage.write(tasks);
            } catch (IOException e) {
                return ui.showError(e);
            }

            return output;
        }
    }
}
