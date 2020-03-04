package duke.task;

import java.time.temporal.TemporalAmount;

/**
 * This interface imposes the ability to be snoozed or postponed on each Task subclass that implements it.
 */
public interface Snoozable<T extends Task> {
    /**
     * Snoozes the task by the specified duration.
     *
     * @param duration the duration to snooze the task for
     */
    T snooze(TemporalAmount duration);
}
