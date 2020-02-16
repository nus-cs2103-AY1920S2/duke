/**
 * Base class for all Tasks
 * TBD: Use an abstract class
 */

package umaikaze.duke.task;

import umaikaze.duke.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    /**
     * WARNING: Dummy method for polymorphism, do not use!
     */
    public String getSaveString() throws DukeException {
        throw new DukeException("Nyot awwowed to save basic task");
    }

    public String getDescription() {
        return description;
    }

    /**
     * @return The status and description of the task only, so it can be called in child classes
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + description;
    }
}