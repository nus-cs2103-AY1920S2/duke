package duke.tasks;

import duke.enums.TType;

import java.time.LocalDate;

public abstract class Task {
    protected String task;
    protected boolean isDone;

    public abstract TType getType();

    public abstract LocalDate getDate();

    public String getTask() {
        return task;
    }
    public boolean getDone() {
        return isDone;
    }
    public void setDone(boolean bool) {
        isDone = bool;
    }

    public Task (String task) {
        this.task = task;
        isDone = false;
    }

    public Task(boolean isDone, String task) {
        this(task);
        this.isDone = isDone;
    }

}
