public class Todo extends Task {

    public Todo(String description) {
        super(description);
        assert description != null : "No description for this deadline";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

