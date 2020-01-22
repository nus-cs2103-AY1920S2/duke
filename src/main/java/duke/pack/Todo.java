package duke.pack;

public class Todo extends Task {
    protected String by;

    /**
     * creates a to-do type of task
     * @param description task to be done
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
