/**
 * Represents a ToDo task which has a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo instance.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Overrides the Task's toString method
     * and it contains the mark and the description of the
     * todo.
     *
     * @return The String that represents the todo's details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

