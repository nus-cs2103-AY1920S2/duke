package task;

import java.io.Serializable;

/**
 * A abstract task to be inherited by specific tasks such as
 * to do, deadline or event.
 * Contains the description and the status of the task.
 */

public abstract class Task implements Serializable {

    private String description;
    private boolean isDone;

    /**
     * Constructor for task.
     * @param description specifies the detail of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status of the task.
     * @return status of the task in string.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Abstract method to get the specific type of the task.
     * @return the specific type of the task in string.
     */
    public abstract String getTypeIcon();

    /**
     * Mark the task as done.
     */
    public void setDone(){
        isDone=true;
    }

    /**
     * To determine whether the description of the task
     * contain a certain keyword.
     * @param keyword given keyword to be checked.
     * @return the boolean that indicate whether the
     * description of the task contain a certain keyword.
     */
    public boolean containKeyword(String keyword){
        if(description.contains(keyword)){
            return true;
        }
        return false;
    }

    /**
     * Get the detail of the task which includes the type of task,
     * the status of the task and the description of the task.
     * @return string contains the standard format of the task.
     */
    @Override
    public String toString() {
        return "["+getTypeIcon()+"] ["+getStatusIcon()+"] "+description;
    }
}
