/**
 * Encapsulates a todo task which has a description
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo instance.
     * @param description Task description
     */
    public Todo(String description) {
        super(description);
    }
    
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), description);
    }
}
