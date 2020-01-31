package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo Task.
     *
     * @param description the description of what has to be done.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo Task when loading from disk.
     *
     * @param description the description of what has to be done.
     * @param isDone      whether or not the task has been done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Formats the Todo task to be saved in the disk.
     *
     * @return the String to be saved in the disk.
     */
    @Override
    public String formatSavingName() {
        return "todo," + description + "," + isDone + "\n";
    }

    /**
     * Returns the string representation of the Todo object.
     *
     * @return the string representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
