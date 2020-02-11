package duke.tasks;

/**
 * Represents a ToDo task.
 *
 * @author Firzan Armani
 */
public class ToDo extends Task {
    /**
     * ToDo constructor.
     *
     * @param todoName Name of the ToDo task
     */
    public ToDo(String todoName) {
        super(todoName);
    }

    /**
     * A method to return the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}