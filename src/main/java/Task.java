import java.util.Date;

public class Task {
    private final String name;
    private Date deadline;
    private final boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public Task(Task t, boolean completed) {
        this.name = t.getName();
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + (completed ? "✓" : "✗") + "] " + name;
    }
}
