package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime eventTime;

    public Event(String taskAction, LocalDateTime event) {
        super(taskAction);
        this.eventTime = event;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.eventTime.format(DateTimeFormatter.ofPattern("" + "MMM d yyyy, h:mm a")));
    }

    @Override
    public String toStringForFileStorage() {
        return super.getStatus() ? String.format("E | 1 | %s | %s", super.getTask(), this.eventTime)
                : String.format("E | 0 | %s | %s", super.getTask(), this.eventTime);
    }
}
