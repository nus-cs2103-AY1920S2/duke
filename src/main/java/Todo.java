/**
 * Todo is a Task and is described by its <code>String</code> description and
 * a <code>boolean</code> isDone to indicate whether a Task is completed.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
