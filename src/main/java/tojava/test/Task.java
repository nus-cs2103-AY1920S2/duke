package tojava.test;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        //empty
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick if task is done and cross if otherwise
     * @return a tick or cross icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    public abstract String getType();

    public abstract String getTask();

    public abstract String getDate();

    public abstract String getTime();

    public abstract String getWord();

    public abstract String changeDate();

    public abstract String changeTime();

    public abstract String getDescription();

    /**
     * Check if a task is done
     * @return 1 if done and 0 otherwise
     */
    public int getDone() {
        if (isDone == true) {
            return 1;
        }
        else {
            return 0;
        }
    }
}