package duke.tasks;

/**
 * A Todo Task taking in only description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String formatText() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + description + "\n";
    }
}

