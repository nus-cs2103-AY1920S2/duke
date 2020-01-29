import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + DateTimeStringFormatter.formatDateTime(this.deadline)
                + ")";
    }
}
