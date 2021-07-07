package duke.task;

/**
 * The Class ToDos are for tasks whose is in a format of Todo.
 */
public class ToDos extends Task {

    /**
     * Instantiates a new to dos.
     *
     * @param description the description
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }
}
