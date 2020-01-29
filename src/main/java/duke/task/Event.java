package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super(description);
        super.taskType = TaskType.E;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime.format(IN_FORMATTER);
    }

    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (at: " + dateTime.format(OUT_FORMATTER) + ")";
    }

    @Override
    public String saveString() {
        return getTaskType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + getDateTime();
    }
}