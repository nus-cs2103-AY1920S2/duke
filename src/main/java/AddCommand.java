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
     * Executes command actions.
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     * @throws DukeException throws a DukeException if description is not added
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if(command.equals("todo")) {
            if(description.equals("")) {
                throw new DukeException("todo");
            } else {
                tasks.addToDo(description);
            }
        } else if (command.equals("deadline")) {
            if(description.equals("")) {
                throw new DukeException("deadline");
            } else {
                String[] array = description.split(" /by ");
                tasks.addDeadline(array[0], array[1]);
            }
        } else {
            if(description.equals("")) {
                throw new DukeException("event");
            } else {
                String[] array = description.split(" /at ");
                tasks.addEvent(array[0], array[1]);
            }
        }
    }
}
