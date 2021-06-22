package duke.task;

import static duke.util.MagicStrings.ERROR_RECURRING_TASK_MISSING_DEADLINE;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

import duke.exception.DuchessException;
import duke.util.Frequency;

public class RecurringDeadline extends Deadline {
    private Frequency frequency;
    private LocalDateTime repeatEndTime;

    /**
     * Initialises the {@code RecurringDeadline} instance with its description, deadline and
     * frequency.
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

    /**
     * Initialises the {@code RecurringDeadline} instance with all of its information. Mainly used
     * by {@code Storage} to regenerate instances.
     *
     * @param description    Written description of the task.
     * @param deadline       {@code LocalDateTime} object indicating the deadline of
     *                       the task.
     * @param frequency      {@code Frequency} of the deadline.
     * @param repeatEndTime  {@code LocalDateTime} object indicating the time to stop repeating.
     * @param isCompleted    State of completion of the deadline.
     * @param completionTime {@code LocalDateTime} object indicating the time of
     *                       completion of the task.
     */
    public RecurringDeadline(String description, LocalDateTime deadline, Frequency frequency,
                             LocalDateTime repeatEndTime, boolean isCompleted, LocalDateTime creationTime,
                             LocalDateTime completionTime, boolean isCompletedOnTime) {
        super(description, deadline, isCompleted, creationTime, completionTime, isCompletedOnTime);
        this.frequency = frequency;
        this.repeatEndTime = repeatEndTime;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + this.getFrequency() + ")";
    }

    @Override
    protected Object clone() throws DuchessException {
        RecurringDeadline clonedRecurringDeadline = (RecurringDeadline) super.clone();
        clonedRecurringDeadline.frequency = this.frequency; // The Frequency enum is immutable
        clonedRecurringDeadline.repeatEndTime = this.repeatEndTime; // LocalDateTime is immutable
        return clonedRecurringDeadline;
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

    @Override
    public void snooze(TemporalAmount snoozePeriod) {
        super.snooze(snoozePeriod);
        if (this.repeatEndTime != null) {
            this.repeatEndTime = this.repeatEndTime.plus(snoozePeriod);
        }
    }

    /**
     * Returns the {@code repeatEndTime} of the {@code RecurringDeadline}.
     *
     * @return Repeat end time in {@code LocalDateTime} format.
     */
    public LocalDateTime getRepeatEndTime() {
        return this.repeatEndTime;
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
