import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(DateTimeFormatter.ofPattern("" +
                "MMM d yyyy, h:mm a")));
    }

    @Override
    public String toFormatString() {
        return super.getStatus() ? String.format("D | 1 | %s | %s", super.getTask(), this.deadline)
                : String.format("D | 0 | %s | %s", super.getTask(), this.deadline);
    }
}
