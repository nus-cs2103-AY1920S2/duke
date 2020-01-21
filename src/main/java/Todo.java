public class Todo extends Task{
    /**
     * Constructor for Todo
     * @param description description of Todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
