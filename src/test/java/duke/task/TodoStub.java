package duke.task;

public class TodoStub extends Todo {
    protected String stringToSaveToDisk;
    protected String statusIcon;
    protected String doneStatusIcon;
    protected String incompleteStatusIcon;
    protected String toString;

    public TodoStub(String description, boolean isDone,
                    String stringToSaveToDisk, String doneStatusIcon,
                    String incompleteStatusIcon, String toString) {
        super(description, isDone);
        this.stringToSaveToDisk = stringToSaveToDisk;
        this.doneStatusIcon = doneStatusIcon;
        this.incompleteStatusIcon = incompleteStatusIcon;
        this.statusIcon = isDone ? doneStatusIcon : incompleteStatusIcon;
        this.toString = toString;
    }

    /**
     * Mark task as done.
     */
    @Override
    public void markAsDone() {
        // Change Status Icon to done
        isDone = true;
        this.statusIcon = doneStatusIcon;
    }

    /**
     * Mark task as incomplete.
     */
    @Override
    public void markAsIncomplete() {
        isDone = false;
        this.statusIcon = incompleteStatusIcon;
    }

    /**
     * Returns a String (Unicode Character) based on duke.task.Task completion status.
     *
     * @return String representing Unicode character for check mark or cross
     */
    @Override
    public String getStatusIcon() {
        return statusIcon;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return toString;
    }

    /**
     * To return a String representation of duke.task.Todo instance
     *
     * @return String representing task in save file
     */
    @Override
    public String stringToSaveToDisk() {
        return stringToSaveToDisk;
    }

    @Override
    public boolean getTaskCompletionStatus() {
        return isDone;
    }
}
