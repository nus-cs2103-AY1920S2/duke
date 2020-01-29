package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime dateTime;

    /**
     * @param description description of task
     * @param dateTime date and time of task
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        super.taskType = TaskType.D;
        this.dateTime = dateTime;
    }

    /**
     * Get the date and time in the format which match in data file.
     *
     * @return string represent the date and time
     */
    public String getDateTime() {
        return dateTime.format(IN_FORMATTER);
    }

    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (by: " + dateTime.format(OUT_FORMATTER) + ")";
    }

    @Override
    public String saveString() {
        return getTaskType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + getDateTime();
    }
}
