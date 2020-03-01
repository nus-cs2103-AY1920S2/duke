/**
 * Represents a todo object characterized by an instrution.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo with the given instruction.
     */
    public ToDo (String instruction) {
        super(instruction);
    }

    /**
     * Gets a string representation of a todo.
     * @return the string representation of this todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
