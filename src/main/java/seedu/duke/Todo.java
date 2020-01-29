package seedu.duke;

import java.time.LocalDate;

/**
 * Subclass of Task to represent a todo.
 */
public class Todo extends Task {
    /**
     * Subclass of Task to represent a todo.
     *
     * @param description details of the todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public LocalDate getTime() {
        return null;
    }
}
