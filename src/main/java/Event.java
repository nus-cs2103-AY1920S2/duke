import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Task {
    private final String name;
    private final boolean completed;
    private final LocalDateTime deadline;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Event(String name, LocalDateTime deadline) {
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

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[E][" + (completed ? "✓" : "✗") + "] " + name + " (at: " +
                deadline.format(formatter) + ")";
    }
}
