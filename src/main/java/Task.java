public class Task {
    private String name;
    private boolean done = false;

    Task(String name) {
        this.name = name;
    }

    boolean isDone() {
        return this.done;
    }

    boolean setToDone() {
        return this.done = true;
    }

    String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "[" + (isDone() ? "✓" : "✗") + "] " + getName();
    }
}
