package task;

/**
 * Represents a Todo class.
 * A Todo instance is a task without time information.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo task.
     *
     * @param name the name of the Todo task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructor for Todo Task.
     *
     * @param name the name of the Todo task
     * @param isDone the boolean value representing the status of the Todo task
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a summary of the Todo task in the file format.
     *
     * @return Returns a summary of the Todo task in the file format
     */
    public String toStringFileFormat() {
        return "T" + super.toStringFileFormat();
    }
}
