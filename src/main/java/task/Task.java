package task;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Timer;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return task.description == this.description;
    }

    public void setUpTimer(HashMap<String, Timer> taskTimerMapping) throws Exception {
        return;
    }
}
