package app.core.tasks;

import app.util.Date;

public class EventTask extends Task {
    private Date when;

    public EventTask(String description, Date when) {
        super(description);
        this.when = when;
    }

    @Override
    public String toStorage() {
        return String.format("E,%s,%b,%s", this.description, this.isDone, this.when.toStorage());
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.description, this.when.toString());
    }
}