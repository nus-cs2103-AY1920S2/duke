package duke.task;

/**
 * The base class for all entries in the tasksList.
 * Used to store details of each Task and provide helper methods to
 * help print tasks status.
 */
public class Task {
    public String description;
    public boolean isDone;

    /**
     * Constructs a Task with a description and marks it if it is done
     *
     * @param description The description of a Task gives additional details of the specific Task
     * @param isDone A Task can be marked as done or finished.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns 'Tick' if the task is done else 'X' symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns us a String representation of the Task
     * useful for printing our task details.
     */
    public String toString(){
        return "[" + getStatusIcon() +"]" + " " + description;
    }
}
