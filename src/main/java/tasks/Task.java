package tasks;

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

    /**
     * Generates the string needed for saving.
     * @return The string needed.
     */
    public abstract String saveString();

    /**
     * Marks task as done.
     */
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
