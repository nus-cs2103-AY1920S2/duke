package duke;

/**
 * Task class representing task type.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor method. Default value of isDone is false.
     * @param description desc
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded constructor specifying isDone value.
     * @param description desc
     * @param mark mark
     */
    public Task(String description, boolean mark) {
        this.description = description;
        this.isDone = mark;
    }

    /**
     * Helper method to get status as a special char symbol (tick or X).
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Method to mark task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Custom toString implementation.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Override saveFormat method to generate neatly formatted information for saving.
     * @return String
     */
    public String saveFormat() {
        return "N" + " , " + (this.isDone ? "1" : "0") + " , " + this.description;
    }
}
