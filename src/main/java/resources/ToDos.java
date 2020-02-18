package resources;

/**
 * ToDo is a type of task that accepts /at statements only
 */
public class ToDos extends Task {
    /**
     * Instantiates a new To dos.
     *
     * @param description the description
     */
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
