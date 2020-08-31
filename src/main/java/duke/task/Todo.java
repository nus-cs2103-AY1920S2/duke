package duke.task;

/**
 * This is a subclass of task which simulates Todotasks.
 */
public class Todo extends Task {

    /**
     * Class constructor inherits Task constructor.
     *
     * @param description Description of this Todotask.
     * @param tags Tags of the task.
     */
    public Todo(String description, String... tags) {
        super(description, tags);
    }

    /**
     * Override String representation of Todotasks.
     *
     * @return Type T and its description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getData() {
        return "T" + super.getData();
    }
}
