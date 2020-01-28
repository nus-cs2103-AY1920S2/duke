package duke.tasks;

import duke.enums.TType;

import java.time.LocalDate;

/**
 * Represents an abstract task to be implemented by different type of task subclasses.
 */
public abstract class Task {
    /** The description of the task. */
    protected String task;
    protected boolean done;

    public abstract TType getType();

    public abstract LocalDate getDate();

    /**
     * @return task description in String format.
     */
    public String getTask() {
        return task;
    }

    /**
     * @return boolean representing whether the task is done.
     */
    public boolean getDone() {
        return done;
    }

    /**
     * @param bool boolean to set isDone to.
     */
    public void setDone(boolean bool) {
        done = bool;
    }

    /**
     * Constructs a Task object with a description and default isDone = false.
     * @param task description of task.
     */
    public Task (String task) {
        this.task = task;
        done = false;
    }

    /**
     * Overloaded constructor that additionally modifies boolean isDone.
     * @param done boolean to set isDone to.
     * @param task description of task.
     */
    public Task(boolean done, String task) {
        this(task);
        this.done = done;
    }

}
