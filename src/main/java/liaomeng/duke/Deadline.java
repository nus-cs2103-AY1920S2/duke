package liaomeng.duke;

import java.time.LocalDate;

/**
 * A task that has a due date.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a deadline task.
     *
     * @param isDone boolean indicating whether the task is marked as done.
     * @param description string description of the task.
     * @param deadline due date of the task.
     * @param level priority level of the task.
     */
    public Deadline(boolean isDone, String description, LocalDate deadline, PriorityLevel level) {
        super(isDone, description, level);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "\n    (by: " + deadline + ", " + deadline.getDayOfWeek() + ")";
    }

    @Override
    public String toSimplerString() {
        return "D//" + super.toSimplerString() + "//" + deadline;
    }
}
