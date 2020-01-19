class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    private Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Todo complete() {
        return new Todo(description, true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
