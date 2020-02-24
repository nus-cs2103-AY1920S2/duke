/**
 * ToDo object for tasks without any date/time attached to it.
 */

public class ToDo extends Task {
    /**
     * Creates a new ToDo object.
     * @param content Description of task to be done.
     */
    public ToDo(String content) {
        super(content);
    }

    /**
     * Returns a string describing the task to be done in the format for saving.
     * @return String describing the task to be done.
     */
    @Override
    public String toStore() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string describing the task to be done in the format for printing.
     * @return String describing the task to be done.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
