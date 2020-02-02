/**
 * This class represents a Todo task.
 */
public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getFullDescription() {
        return "[T]" + super.getDescriptionWithIsDone();
    }
}