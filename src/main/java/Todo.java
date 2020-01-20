public class Todo extends Task{
    public Todo(String description, String by) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}