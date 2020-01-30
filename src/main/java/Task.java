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
            // return a tick
            return "\u2713";
        } else {
            // return a cross
            return "\u2718";
        }
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String status = this.getStatusIcon();
        String description = this.getDescription();
        return "[" + status + "] " + description;
    }
}
