/** Represents a simple Task which has only a description. */
public class Todo extends Task {

    /**
     * Constructor for Todo class.
     *
     * @param desc Task description.
     */
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}