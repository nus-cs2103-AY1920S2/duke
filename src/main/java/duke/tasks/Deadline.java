package duke.tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        super.taskType = TaskType.D;
        this.by = by;
    }

    public String getDateTime() {
        return by.format(IN_FORMATTER);
    }

    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (by: " + by.format(OUT_FORMATTER) + ")";
    }

    public String saveString() {
        return getTaskType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + getDateTime();
    }
}
