package duke;

/**
 * Represents an Abstract Task (which could either be Deadline, Todo, or Event).
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a given Task object.
     *
     * @param name The name for the Task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructs a given Task object.
     *
     * @param name The name for the Task.
     * @param isDone Indicates whether the Task has been completed or not.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns the name of the Task.
     *
     * @return The Task name.
     */
    public String getName() {
        return name;
    }

    /**
     * Marks a Task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the status of the Task, whether done or not.
     *
     * @return True, if the Task has been completed or done, false otherwise.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Returns a short character differentiating between the various types of classes.
     *
     * @return A string containing the above  mentioned  short-character.
     */
    public abstract String getMnemonic();

    @Override
    public String toString() {
        return ("[" + (isDone ? "\u2713" : "\u2718") + "] " + name);
    }
}
