package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;

/**
 * Deadline is a subclass of Task. It represents a task with a description and
 * completion status, as well as a due date.
 */
public class Deadline extends Task implements Snoozable<Deadline> {
    protected final LocalDateTime deadline;

    /**
     * Instantiates a Deadline object with the specified description and due
     * date, marked as incomplete.
     *
     * @param description the description of the deadline
     * @param deadline the due date of the deadline
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description, false);
        this.deadline = deadline;
    }

    /**
     * Instantiates a Deadline object with the specified description, due date,
     * and completion status.
     *
     * @param description the description of the deadline
     * @param deadline the due date of the deadline
     * @param isCompleted the completion status of the deadline
     */
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
