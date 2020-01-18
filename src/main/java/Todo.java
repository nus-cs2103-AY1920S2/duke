public class Todo implements Task{
    private final String name;
    private final boolean completed;

    public Todo(String name) {
        this.name = name;
        this.completed = false;
    }

    public Todo(Todo t, boolean completed) {
        this.name = t.getName();
        this.completed = completed;
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompletion() {
        return this.completed;
    }

    public Todo makeCompleted() {
        return new Todo(this, true);
    }

    @Override
    public String toString() {
        return "[T][" + (completed ? "✓" : "✗") + "] " + name;
    }
}
