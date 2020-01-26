public class Todo extends Task {
    /**
     * Constructor for Todo
     * @param description description of Todo
     */
    public Todo(String description) {
        super(description);
        type = "todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
