package duke;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getTask() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
