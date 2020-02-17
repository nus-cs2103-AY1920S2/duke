package resources;

import Duke.UI;
import exceptions.DukeException;

/**
 * The type Resources.Task.
 */
public class Task {

    private String Description = "";
    protected boolean isDone = false;

    /**
     * Instantiates a new Resources.Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.Description = description;
    }

    /**
     * Reset desc.
     *
     * @param desc the desc
     */
    protected void resetDesc(String desc){
        Description = desc;
    }

    /**
     * Mark done.
     *
     * @throws DukeException the duke exception
     */
    public void markDone() throws DukeException{
        if(isDone){
            throw new DukeException(UI.getReply("doneAlready"));
        }
        isDone = true;
    }

    /**
     * Gets status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + Description;
    }
}
