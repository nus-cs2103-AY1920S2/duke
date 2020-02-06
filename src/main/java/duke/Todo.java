package duke;

public class Todo extends Task {
    public Todo(String description) {
        super(description, false);
    }

    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public Todo complete() {
        return new Todo(description, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
