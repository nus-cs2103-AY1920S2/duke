public class Event implements Task {
    private final String name;
    private final boolean completed;
    private final String deadline;

    public Event(String name, String deadline) {
        this.name = name;
        this.completed = false;
        this.deadline = deadline;
    }

    public Event(Event event, boolean completed) {
        this.name = event.getName();
        this.completed = completed;
        this.deadline = event.getDeadline();
    }

    public Event makeCompleted() {
        return new Event(this, true);
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
        return "[E][" + (completed ? "✓" : "✗") + "] " + name + " (at: " + deadline + ")";
    }
}
