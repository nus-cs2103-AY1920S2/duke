package duke.task;

import java.time.LocalDateTime;

public class EventTask extends Task {
    public static final char ICON = 'E';

    private LocalDateTime at;

    public EventTask(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public EventTask(String description, LocalDateTime at,
            boolean isCompleted) {
        super(description, isCompleted);
        this.at = at;
    }

    public LocalDateTime getDateTime() {
        return at;
    }

    @Override
    public char getTaskIcon() {
        return EventTask.ICON;
    }

    @Override
    public String toStringDelimited() {
        return String.format("%c %c %s %c %s", getTaskIcon(), Task.DELIMITER,
                super.toStringDelimited(), Task.DELIMITER,
                getDateTime().format(Task.DATE_TIME_OUTPUT_FORMAT));
    }
    
    @Override
    public String toString() {
        return String.format("[%c]%s (at: %s)", getTaskIcon(),
                super.toString(),
                getDateTime().format(Task.DATE_TIME_OUTPUT_FORMAT));
    }
}
