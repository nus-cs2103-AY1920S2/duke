package duke.task;

/**
 * Creates a todo task.
 */
public class ToDo extends Task {
    public ToDo(String msg) {
        super(msg);
        super.type = "T";
    }
}
