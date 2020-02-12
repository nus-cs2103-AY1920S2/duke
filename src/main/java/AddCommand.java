/**
 * Represents a command that adds item to list. A <code>AddCommand</code> object
 * corresponds to a command represented by the command and a description e.g.,
 * <code>"deadline", "read /by 2019-05-10 1800"</code>
 */
public class AddCommand extends Command {

    /**
     * A constructor for AddCommand object.
     * @param command command called
     * @param description description of task
     */
    public AddCommand(String command, String description) {
        super(command, description);
    }

    /**
     * Executes add command actions.
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     * @return A string containing output message
     * @throws DukeException throws a DukeException if description is not added
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String output = "";
        boolean isTodo = command.equals("todo");
        boolean isDeadline = command.equals("deadline");
        boolean isDescEmpty = description.equals("");

        if (isTodo) {
            if (isDescEmpty) {
                throw new DukeException("todo");
            } else {
                output = tasks.addToDo(description);
            }
        } else if (isDeadline) {
            if (isDescEmpty) {
                throw new DukeException("deadline");
            } else {
                String[] descArray = description.split(" /by ");

                output = tasks.addDeadline(descArray[0], descArray[1]);
            }
        } else {
            if (isDescEmpty) {
                throw new DukeException("event");
            } else {
                String[] descArray = description.split(" /at ");

                output = tasks.addEvent(descArray[0], descArray[1]);
            }
        }

        return output;
    }
}
