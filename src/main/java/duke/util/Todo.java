package duke.util;

/**
 * Todo
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class Todo extends Task {
    /**
     * Constructs a Todo instance.
     * @param description The description or details of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the Object's toString method
     * and contains the status icon and description of the todo.
     * @return The String that containing the Todo's details.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
