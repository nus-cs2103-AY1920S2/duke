package tasks;

/**
 * Represents a simple task with description only.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts task into format used in save file.
     *
     * @return Save format of task in a string for easier back-conversion.
     */
    @Override
    public String toSaveFormat() {
        char d = super.getIsDone() ? '1' : '0';
        return "T | " + d + " | " + super.getDescription();
    }
}
