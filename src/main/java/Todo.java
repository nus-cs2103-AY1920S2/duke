class Todo implements Task {
    protected String name;
    protected boolean isDone;

    public Todo(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String status = isDone ? "\u2713" : "\u2718";
        return String.format("[%s] %s", status, name);
    }
}