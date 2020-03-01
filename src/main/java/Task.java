import java.time.LocalDateTime;

/**
 * <h1> Task </h1>
 * The Task class represents the task the user inputs into the Duke program.
 * Its attributes contain a boolean value, which tracks whether the task has been completed.
 * And a String object, which contains the description of the task.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * The getStatusIcon returns a string that contains the icon depending on the value of the "isDone" attribute.
     * @return String the icon representing the "isDone" boolean value
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * The markAsDone method changes the boolean value of the "isDone" attribute to True.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        String icon = (isDone ? "\u2713" : "\u2718");
        return "[" + icon + "]" + " " + this.description;
    }

    public boolean contains(String keyWord) {
        return this.description.contains(keyWord);
    }

    public String toFileString() {
        int status = (isDone ? 1 : 0);
        return " | " + status + " | " + this.description;
    }

    public String getType() {return "";};
    public void edit(LocalDateTime dt){};
}