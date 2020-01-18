public class Deadline implements Task {
    private final String name;
    private final boolean completed;
    private final String deadline;

    public Deadline(String name, String deadline) {
        this.name = name;
        this.completed = false;
        this.deadline = deadline;
    }

    public Deadline(Deadline d, boolean completed) {
        this.name = d.getName();
        this.completed = completed;
        this.deadline = d.getDeadline();
    }

    public Deadline makeCompleted() {
        return new Deadline(this, true);
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompletion() {
        return this.completed;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D][" + (completed ? "✓" : "✗") + "] " + name + " (by: " + deadline + ")";
    }
}
