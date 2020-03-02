package duke.task;

/**
 * Represents a Todo task.
 * It has a description, and is marked as done or not.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task instance.
     * @param description The description of the todo task.
     * @param isDone Whether the todo task is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Sets a new time for the todo task.
     * @param newTime The new time specified by the user.
     */
    public void setTime(String newTime) {
    }

    @Override
    public String toString() {
        String str = "[T]";
        if (this.isDone) {
            str += "[O] ";
        } else {
            str += "[X] ";
        }
        str += this.description;
        return str;
    }
}