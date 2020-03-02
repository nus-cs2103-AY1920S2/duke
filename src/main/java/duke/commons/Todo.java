package duke.commons;

/**
 * Represents a type of task that could be added. A <code>Todo</code> object corresponds to
 * a task with no specified date or time.
 */

public class Todo extends Task {

    protected String type;

    /**
     * Constructor for <code>Todo</code>.
     * @param type <code>String</code> representing the type of the <code>Task</code> ("todo").
     * @param isDone true if the <code>Task</code> is completed, false otherwise.
     * @param description <code>String</code> representing the description of the <code>Task</code>.
     */
    public Todo(String type, boolean isDone, String description) {
        super(type, isDone, description);
    }

    /**
     * Returns a <code>String</code> object representing the type of this <code>Task</code>.
     *
     * @return the <code>String</code> "T".
     */
    @Override
    public String getTypeIcon() {
        return "T";
    }

    /**
     * Returns an array of <code>String</code> objects representing this <code>Todo</code>.
     *
     * @return a string array representation of the <code>Todo</code> object.
     */
    @Override
    public String[] toDataTokens() {
        String isDoneString = String.valueOf(super.isDone);
        return new String[] {getTypeIcon(), isDoneString, super.description};
    }

    /**
     * Returns a <code>String</code> object representing this <code>Todo</code>.
     *
     * @return a string representation of the <code>Todo</code> object.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}