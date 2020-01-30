package duke.task;

/**
 * Represents a Todo task that extends the Task class. A Todo object is represented by a String description and boolean
 * isDone.
 */
public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}