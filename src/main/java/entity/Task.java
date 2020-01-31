package entity;

import java.util.Date;

public abstract class Task {

    private boolean isDone;
    private String taskName;
    private String addedInfo;

    public Task() {
        this.isDone = false;
        this.taskName = "";
    }

    public Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone(boolean done) {
        isDone = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getAddedInfo() {
        return addedInfo;
    }

    public void setAddedInfo(String addedInfo) {
        this.addedInfo = addedInfo;
    }

    public abstract String printTask();

    public abstract boolean isDue(Date date);
}
