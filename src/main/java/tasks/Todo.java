package tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        char d = super.getIsDone() ? '1' : '0';
        return "T | " + d + " | " + super.getDescription();
    }
}
