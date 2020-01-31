package duke.tasks;

/**
 * Task class represents a task indicated by user.
 */
public class Task {
    String description;
    boolean isDone;

    /**
     * Creates an incomplete task.
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task.
     *
     * @param description description of task.
     * @param done boolean indicating if task is completed.
     */
    public Task(String description, int done) {
        this.description = description;
        if (done == 1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Formats task for save to database.
     *
     * @return formatted string that represents task.
     */
    public String toPrint() {
        if (this.isDone) {
            return "T | " + 1 + " | " + this.description;
        } else {
            return "T | " + 0 + " | " + this.description;
        }
    }

    /**
     * Marks task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Formats task for printing.
     *
     * @return formatted string that represents task.
     */
    public String toString() {
        if (this.isDone) {
            return "[✓] " + this.description;
        } else {
            return "[✗] " + this.description;
        }
    }
}