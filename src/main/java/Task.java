/**
 * Represents a parent Task object from which tasks of different categories inherit from.
 * Each Task object and it's children will have a description String, as well as a boolean isDone to indicate if the task is completed.
 */
class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        if (isDone) {
            // return a tick
            return "\u2713";
        } else {
            // return a cross
            return "\u2718";
        }
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String status = this.getStatusIcon();
        String description = this.getDescription();
        return "[" + status + "] " + description;
    }
}
