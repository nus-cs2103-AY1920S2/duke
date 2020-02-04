package dukeApp.files;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type of task
     *
     * @return the letter indicating the task type
     */
    public String getType() {
        return "T";
    }

    /**
     * Returns only the task description without the date
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task date description
     *
     * @return task date description
     */
    @Override
    public String toString() {
        return description;
    }
}
