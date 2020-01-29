package duke.tasks;

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

    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (at: " + dateTime.format(OUT_FORMATTER) + ")";
    }

    public String saveString() {
        return getTaskType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + getDateTime();
    }
}