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

    @Override
    public String getDescription() {
        return "";
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

    public String storageFormat() {
        return description;
    }
}
