package task;

import core.DateUtil;

import java.time.LocalDate;

/**
 * Specific type of task that contains description and recurs weekly.
 */
public class RecurringTask extends Task {

    private LocalDate date;
    private final int daysInAWeek = 7;

    /**
     * Constructor for the event task.
     *
     * @param description is the detail of the task.
     * @param date        is the event time of the task.
     */
    public RecurringTask(String description, LocalDate date) {
        super(description, "R");
        this.date = date;
    }

    public void recurs() {
        date = date.plusDays(daysInAWeek);
    }

    /**
     * Gets the detail of the standard task and the duration of the task.
     *
     * @return string contains the standard format of the event.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + DateUtil.standardFormat(date) + ")";
    }


}
