package duke.tasks;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.date = null;
    }

    public Task(String description, LocalDate date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    public abstract String getType();
    public abstract String saveString();

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone? "[\u2713]" : "[\u2718]");
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}
