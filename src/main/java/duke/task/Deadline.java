package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private LocalDateTime dateTime;

    /**
     * Creates an instances of Deadline.
     *
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
    public String saveString() {
        String status = getStatus() ? "1" : "0";
        return String.format("%s | %s | %s | %s", getTaskType(), status, getDescription(), getDateTime());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)",super.getTaskType(), super.toString(),
                dateTime.format(OUT_FORMATTER));
    }
}
