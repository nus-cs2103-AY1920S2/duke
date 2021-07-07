/**
 * Represents an abstract class, Task.
 * A Task has a description and a complete status.
 */
abstract class Task {
    String description;
    private boolean isDone;

    /**
     * Creates a Task.
     *
     * @param description A String description of the Task.
     * @param isDone      A boolean on whether the Task is complete.
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * An abstract task to complete the Task.
     *
     * @return A completed Task with the same description.
     */
    abstract Task completeTask();

    /**
     * Obtain a status icon, depending on the complete status.
     *
     * @return A String status icon.
     */
    String getStatusIcon() {
        return (isDone ? "\u2714" : "\u274C"); //return tick or X symbols
    }

    boolean getCompletionStatus() {
        return this.isDone;
    }

    boolean match(String keywords) {
        return this.description.toLowerCase().contains(keywords);
    }
}
