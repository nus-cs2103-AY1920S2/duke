package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;

public class Deadline extends Task implements Snoozable<Deadline> {
    protected final LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description, false);
        this.deadline = deadline;
    }

    public Deadline(String description, LocalDateTime deadline, boolean isCompleted) {
        super(description, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public Deadline complete() {
        return new Deadline(description, deadline, true);
    }

    @Override
    public Deadline snooze(TemporalAmount duration) {
        return new Deadline(description, deadline.plus(duration), isCompleted);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")));
    }
}
