package duke.task;

public class Todo extends Task {

    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    public String toSaveFormat() {
        return String.format("T | %s", super.toSaveFormat());
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}