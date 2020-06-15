package resources;

import duke.UI;
import exceptions.DukeException;

import java.time.LocalDate;

/**
 * The type Resources.Task.
 */
public class Task {

    private String Description = "";
    protected boolean isDone = false;
    private LocalDate doneDate;

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
    protected void resetDesc(String desc) {
        Description = desc;
    }

    /**
     * Mark done and set the date done time
     *
     * @throws DukeException the duke exception
     */
    public void markDone() throws DukeException {
        if (isDone) {
            throw new DukeException(UI.getReply("doneAlready"));
        }
        doneDate = LocalDate.now();
        TaskTracker.finishTask(this);
        isDone = true;
    }

    /**
     * Mark done and set the date done time
     *
     * @return LocalDate the Date this thing was done
     */
    public LocalDate getDoneDate() {
        return this.doneDate;
    }

    /**
     * Sets the done date of tasks
     * For Storage reloading function
     *
     * @param d LocalDate the Date this thing was done
     */
    public void setDone(LocalDate d) {
        this.doneDate = d;
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
