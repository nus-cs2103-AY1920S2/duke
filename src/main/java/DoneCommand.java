/**
 * Represents a done command that alters a specific item to done. A <code>DoneCommand</code> object
 * corresponds to a command represented by the command and a description e.g.,
 * <code>"done", "1"</code>
 */
public class DoneCommand extends Command {

    /**
     * A constructor to create a Done command object.
     * @param command command string
     * @param description description of command called
     */
    public DoneCommand(String command, String description) {
        super(command, description);
    }

    /**
     * Executes command actions.
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     * @return A string containing the output
     * @throws DukeException throws a DukeException if description is not added
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        boolean isDescEmpty = description.equals("");

        if (isDescEmpty) {
            throw new DukeException("done");
        } else {
            int num = Integer.parseInt(description);

            String output = tasks.setDone(num);

            return output;
        }
    }
}
