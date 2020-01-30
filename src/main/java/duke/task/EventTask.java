package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    public static final char ICON = 'E';
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMAT
            = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static final DateTimeFormatter DATE_TIME_OUTPUT_FORMAT
            = DateTimeFormatter.ofPattern("EE, dd MMM yyyy, HH:mm");

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
                getDateTime().format(DeadlineTask.DATE_TIME_OUTPUT_FORMAT));
    }
    
    @Override
    public String toString() {
        return String.format("[%c]%s (at: %s)", getTaskIcon(),
                super.toString(),
                getDateTime().format(EventTask.DATE_TIME_OUTPUT_FORMAT));
    }
}