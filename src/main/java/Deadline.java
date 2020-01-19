public class Deadline extends Task {
    private String by_schedule;

    public Deadline(String description, String by_schedule) {
        super(description);
        this.by_schedule = by_schedule;
    }

    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)", getStatusIcon(), this.description, this.by_schedule);
    }
}
