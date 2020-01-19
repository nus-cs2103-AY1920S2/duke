class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task complete() {
        return new Task(description, true);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "\u2713" : "\u2718", description);
    }
}
