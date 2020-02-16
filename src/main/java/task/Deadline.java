package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime deadlineTime;

    public Deadline(String taskAction, LocalDateTime deadlineTime) {
        super(taskAction);
        this.deadlineTime = deadlineTime;
    }

    public LocalDateTime getDeadlineTime() {
        return this.deadlineTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadlineTime.format(DateTimeFormatter.ofPattern("" + "MMM d yyyy, h:mm a")));
    }

    @Override
    public String toStringForFileStorage() {
        return super.getStatus() ? String.format("D | 1 | %s | %s", super.getTaskAction(), this.deadlineTime)
                : String.format("D | 0 | %s | %s", super.getTaskAction(), this.deadlineTime);
    }
}
