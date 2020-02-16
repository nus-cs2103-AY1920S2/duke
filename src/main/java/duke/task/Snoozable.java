package duke.task;

import java.time.temporal.TemporalAmount;

public interface Snoozable<T> {
    T snooze(TemporalAmount duration);
}
