/**
 * Represents a command to add a task with deadline to the task list,
 * to be executed later.
 */
public class AddDeadlineCommand extends AddCommand {
    /**
     * Creates an AddDeadlineCommand based on the description of the task,
     * and the deadline.
     *
     * @param description the description of the task
     * @param deadline the string representing the deadline of the task
     */
    public AddDeadlineCommand(String description, String deadline) {
        task = new Deadline(description, deadline);
    }
}
