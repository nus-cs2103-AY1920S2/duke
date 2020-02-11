package duke.tasks;

/**
 * Encapsulates a todo task which has a description.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo instance.
     * @param description Task description
     */
    public ToDo(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}
