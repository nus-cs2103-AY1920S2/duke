package duke.util;

/*
 * Task
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * <p>The Task abstract class defines some basic properties and
 * behaviors that every task have. The tasks are set to be not done
 * initially.</p>
 * @author Mario Lorenzo
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task instance with isDone variable set to be
     * false.
     * @param description The description or the details of the task.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * markAsDone method marks the task to be done by
     * setting the isDone property of a task to be true.
     */

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * getStatusIcon method gets the appropriate icon
     * of the task based on whether the task is done or not.
     * @return The appropriate icon. A tick if the task is done, a cross, otherwise.
     */

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }
}
