// Task class and it's children classes
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String status = this.getStatusIcon();
        return "[" + status + "] " + this.getDescription();
    }
}
