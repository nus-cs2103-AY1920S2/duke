package app.core.tasks;

import app.util.Date;

class EventTask extends Task {
    private Date when;

    EventTask(String description, Date when) {
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        
        EventTask otherTask = (EventTask) other;
        boolean hasSameDescription = this.description.equals(otherTask.description);
        boolean hasSameWhen = this.when.equals(otherTask.when);
        return hasSameDescription && hasSameWhen;
    }
}