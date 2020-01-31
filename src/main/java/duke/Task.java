package duke;

/**
 * An abstract object of task, has a name and a done status.
 */
public abstract class Task {
    protected String name;
    private boolean isDone;

    /**
     * Constructs an abstract task, which is not done.
     * @param name The name of event task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructs a task with a protected constructor, in which the done status can be specified.
     * @param name The name of event task
     * @param isDone The done status of event task
     */
    protected Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * States the name and done status of a task.
     * @return The string of the task.
     */
    public String toString() {
        String done = isDone ? "\u2713" : "\u2718";
        return "[" + done + "] " + name;
    }

    /**
     * Returns boolean of done status of the task.
     * @return Boolean of the done status of the task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     * @return A string of the name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a new task with specified done status.
     * @return A new task object.
     */
    public abstract Task setDone();

    /**
     * Returns a string of the task object to be written to a file.
     * @return A string of the task to be written to a file.
     */
    public abstract String writeDrive();
}