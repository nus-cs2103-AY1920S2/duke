package tasks;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("%s%s", "[T]", super.toString());
    }
}
