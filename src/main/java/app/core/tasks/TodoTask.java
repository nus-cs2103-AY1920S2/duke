package app.core.tasks;

class TodoTask extends Task {
    TodoTask(String description) {
        super(description);
    }

    @Override
    public String toStorage() {
        return String.format("T,%s,%b", this.description, this.isDone);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        
        TodoTask otherTask = (TodoTask) other;
        boolean hasSameDescription = this.description.equals(otherTask.description);
        return hasSameDescription;
    }
}