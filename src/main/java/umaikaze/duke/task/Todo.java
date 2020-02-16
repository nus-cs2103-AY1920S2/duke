package umaikaze.duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getSaveString() {
        return "T|" + (isDone ? 1 : 0) + "|" + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
