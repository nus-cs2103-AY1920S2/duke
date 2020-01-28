import java.io.Serializable;
//Adapted from https://nus-cs2103-ay1920s2.github.io/website/schedule/week2/project.html

/**
 * Encapsulates an abstract task of the user.
 * The `Task` object minimally stores a task description,
 * and a flag for whether the user has marked the task as completed.
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    
    private Task() {
    }
    
    /**
     * Constructs a new Task instance.
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a Unicode status icon representing if this task is marked as completed.
     * @return Icon string
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    
    /**
     * Returns whether this task is marked as completed.
     * @return whether task is marked as completed
     */
    public boolean isDone() {
        return isDone;
    }
    
    /**
     * Marks this task is marked as completed.
     */
    public void markAsDone() {
        isDone = true;
    }
}
