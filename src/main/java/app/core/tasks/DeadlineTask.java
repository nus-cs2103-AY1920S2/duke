package app.core.tasks;

import app.util.Date;

class DeadlineTask extends Task {
    private Date deadline;

    DeadlineTask(String description, Date deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toStorage() {
        return String.format("D,%s,%b,%s", this.description, this.isDone, this.deadline.toStorage());
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.deadline.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        
        DeadlineTask otherTask = (DeadlineTask) other;
        boolean hasSameDescription = this.description.equals(otherTask.description);
        boolean hasSameDeadline = this.deadline.equals(otherTask.deadline);
        return hasSameDescription && hasSameDeadline;
    }
}