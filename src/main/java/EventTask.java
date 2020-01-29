public class EventTask extends Task {
    private String when;

    public EventTask(String description, String when) {
        super(description);
        this.when = when;
    }

    @Override
    public String toStorage() {
        return String.format("E,%s,%b,%s", this.description, this.isDone, this.when);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getStatusIcon(), this.description, this.when);
    }
}