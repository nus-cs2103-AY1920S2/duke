package duke.command;

/**
 * Parent class of ToDos, Events and Deadlines.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String datetime = "";

    /**
     * Constructs a Task object.
     *
     * @param description The activity description
     */
    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "V" : "X"); // return tick or X symbols
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns String to be stored in task text file.
     *
     * @return String to be stored in task text file
     */
    public String fileString() {
        return "D|" + getStatusIcon() + "|" + description;
    }

    public boolean containsString(String keyword) {
        return this.toString().contains(keyword);
    }

}
