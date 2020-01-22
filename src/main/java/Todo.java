public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s\n", super.toString());
    }
}
