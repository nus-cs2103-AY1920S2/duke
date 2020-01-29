package duchess.task;

import duchess.util.DateTimeStringFormatter;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDateTime deadline, boolean isCompleted) {
        super(description, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + DateTimeStringFormatter.formatDateTime(this.deadline, this.isCompleted())
                + ")";
    }
}
