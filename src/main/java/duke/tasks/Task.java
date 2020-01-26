package duke.tasks;

import duke.enums.TType;

import java.time.LocalDate;

public abstract class Task {
    protected String task;
    protected boolean done;

    public abstract TType getType();

    public abstract LocalDate getDate();

    public String getTask() {
        return task;
    }
    public boolean getDone() {
        return done;
    }
    public void setDone(boolean bool) {
        done = bool;
    }

    public Task (String task) {
        this.task = task;
        done = false;
    }

    public Task(boolean done, String task) {
        this(task);
        this.done = done;
    }

}
