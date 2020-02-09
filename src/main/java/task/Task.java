package task;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getShortName() {
        return "task.Task";
    }

    public String getStatusIcon() {
        return (isDone ? "2713" : "2718"); //return tick or X symbols
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getFullDetail(Integer i) {
        return "";
    }
}
