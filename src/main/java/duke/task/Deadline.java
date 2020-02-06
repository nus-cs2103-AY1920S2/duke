package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected final LocalDate deadline;

    Deadline(String description, LocalDate deadline) {
        super(description, false);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDate deadline, boolean isCompleted) {
        super(description, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public Deadline complete() {
        return new Deadline(description, deadline, true);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
