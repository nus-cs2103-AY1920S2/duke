package duke.task;

/**
 * Represents a Task(i.e. Todo) and a TaskDate(Which represented a Task with Date element(s) i.e. Deadline and Event).
 * A Task object corresponds to a Task represented by a String description and a boolean isDone. TaskDate (abstract,
 * Deadline and Event extend from this class) and Todo classes extend from this class.
 */
public class Task {
    public String description;
    public boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a String corresponding to the Task's status represented by the boolean isDone.
     * @return "Y" if isDone is true and "X" if isDone is false
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "X"); //return Y or X symbols
    }

    /**
     * Returns the description of the Task.
     * @return Description of Task
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "["+ getStatusIcon() + "] " + getDescription();
    }
}
