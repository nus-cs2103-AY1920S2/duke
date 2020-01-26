package main.duke.tasks;

import main.duke.enums.TType;

import java.time.LocalDate;

public abstract class Task {
    protected int id;
    protected String task;
    protected boolean done;

    public abstract TType getType();

    public abstract LocalDate getDate();

    public int getId() {
        return id;
    }
    public String getTask() {
        return task;
    }
    public boolean getDone() {
        return done;
    }
    public void setDone(boolean bool) {
        done = bool;
    }

    public Task (int id, String task) {
        this.id = id;
        this.task = task;
        done = false;
    }

    public Task(int id, boolean done, String task) {
        this(id, task);
        this.done = done;
    }

}
