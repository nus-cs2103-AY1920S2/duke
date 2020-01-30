/**
 * Represents a command to add a task to the task list,
 * to be executed later.
 */
public class AddTodoCommand extends AddCommand {
    /**
     * Creates an AddEventCommand based on the description of the task
     *
     * @param description the description of the event
     */
    public AddTodoCommand(String description) {
        task = new Todo(description);
    }
}
