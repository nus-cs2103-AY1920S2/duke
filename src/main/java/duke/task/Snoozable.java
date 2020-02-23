package duke.task;

import java.time.temporal.TemporalAmount;

/**
 * Snoozable is an interface for tasks that can be snoozed or postponed.
 */
public interface Snoozable<T extends Task> {
    /**
     * Snoozes the task by the specified duration.
     *
     * @param duration the duration to snooze the task for
     */
    T snooze(TemporalAmount duration);
}
