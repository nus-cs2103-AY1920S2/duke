/**
 * This represents a ToDo task, which is a subset of the class Task.
 * ToDo does not have a date associated.
 */

public class ToDo extends Task {

    /**
     * Creates a new ToDo task.
     * @param description This is the description of the ToDo task.
     */

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
