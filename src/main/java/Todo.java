public class Todo extends Task {

    protected String by;

    public Todo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}