package e0148811.duke;

public class Todo extends Task {
    public Todo(boolean isDone, String description, PriorityLevel level) {
        super(isDone, description, level);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSimplerString() {
        return "T//" + super.toSimplerString();
    }
}
