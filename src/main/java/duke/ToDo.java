package duke;

/**
 * ToDo is a task with description only.
 */
public class ToDo extends Task {

    /**
     * Constructor that takes in description of task.
     *
     * @param description of task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
