package dude;

import java.time.LocalDate;

public abstract class Task {
    private final String details;
    private boolean isDone;

    public Task(String details, boolean isDone) {
        this.details = details;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDetails() {
        return details;
    }

    abstract boolean occursOn(LocalDate date);

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), details);
    }

    public abstract String storeFormat();

    protected String getStatusIcon() {
        return (isDone ? "O" : "X"); 
    }
}