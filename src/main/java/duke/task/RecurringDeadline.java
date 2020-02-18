package duke.task;

import duke.exception.DuchessException;
import duke.util.Frequency;

import java.time.LocalDateTime;

import static duke.util.MagicStrings.ERROR_RECURRING_TASK_MISSING_DEADLINE;

public class RecurringDeadline extends Deadline {
    private Frequency frequency;
    private LocalDateTime repeatEndTime;

    /**
     * Initialises the {@code RecurringDeadline} instance with its description, deadline and
     * completion status.
     *
     * @param description Written description of the task.
     * @param deadline    {@code LocalDateTime} object indicating the deadline of
     *                    the task.
     * @param frequency   {@code Frequency} of the deadline.
     */
    public RecurringDeadline(String description, LocalDateTime deadline, Frequency frequency) {
        super(description, deadline);
        this.frequency = frequency;
    }

    /**
     * Initialises the {@code RecurringDeadline} instance with its description, deadline and
     * completion status.
     *
     * @param description   Written description of the task.
     * @param deadline      {@code LocalDateTime} object indicating the deadline of
     *                      the task.
     * @param frequency     {@code Frequency} of the deadline.
     * @param repeatEndTime {@code LocalDateTime} object indicating the time to stop repeating.
     */
    public RecurringDeadline(String description, LocalDateTime deadline, Frequency frequency,
                             LocalDateTime repeatEndTime) {
        super(description, deadline);
        this.frequency = frequency;
        this.repeatEndTime = repeatEndTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + this.getFrequency() + ")";
    }

    @Override
    public void completeTask() {
        LocalDateTime nextDeadline;
        switch (this.frequency) {
        case DAILY:
            nextDeadline = this.deadline.plusDays(1);
            break;
        case WEEKLY:
            nextDeadline = this.deadline.plusWeeks(1);
            break;
        case FORTNIGHTLY:
            nextDeadline = this.deadline.plusWeeks(2);
            break;
        case MONTHLY:
            nextDeadline = this.deadline.plusMonths(1);
            break;
        default:
            throw new DuchessException(ERROR_RECURRING_TASK_MISSING_DEADLINE);
        }
        if (repeatEndTime == null || nextDeadline.isBefore(repeatEndTime)) {
            this.deadline = nextDeadline;
            return;
        }
        super.completeTask();
    }

    private String getFrequency() throws DuchessException {
        switch (this.frequency) {
        case DAILY:
            return "Daily";
        case WEEKLY:
            return "Weekly";
        case FORTNIGHTLY:
            return "Fortnightly";
        case MONTHLY:
            return "Monthly";
        default:
            throw new DuchessException(ERROR_RECURRING_TASK_MISSING_DEADLINE);
        }
    }
}
