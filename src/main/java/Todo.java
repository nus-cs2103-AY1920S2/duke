public class Todo extends Task {

    protected String by;

    /**
     * Constructor for Todo class.
     *
     * @param description Description for Todo task.
     * @throws DukeException If description is missing.
     */
    public Todo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Same as toString.
     *
     * @return Todo toString
     */
    public String saveToList() {
        return "[T]" + super.toString();
    }
}