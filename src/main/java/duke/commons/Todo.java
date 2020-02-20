package duke.commons;

/**
 * Represents a type of task that could be added. A <code>Todo</code> object corresponds to
 * a task with no specified date or time.
 */

public class Todo extends Task {

    protected String type;

    public Todo(String type, boolean isDone, String description) {
        super(type, isDone, description);
    }

    /**
     * Returns a <code>String</code> object representing the type of this <code>Task</code>.
     *
     * @return the <code>String</code> "T".
     */
    public String getTypeSymbol() {
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
        return new String[] {getTypeSymbol(), isDoneString, super.description};
    }

    /**
     * Returns a <code>String</code> object representing this <code>Todo</code>.
     *
     * @return a string representation of the <code>Todo</code> object.
     */
    @Override
    public String toString() {
        return "[" + getTypeSymbol() + "]" + super.toString();
    }
}