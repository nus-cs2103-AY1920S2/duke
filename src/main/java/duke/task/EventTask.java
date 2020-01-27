package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class EventTask extends Task {
    public static final char ICON = 'E';

    private LocalDateTime at;

    public EventTask(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public LocalDateTime getDateTime() {
        return at;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter
                                    .ofPattern("EE, dd MMM yyyy, HH:mm");
        return String.format(
                "[%c]%s (at: %s)",
                EventTask.ICON, super.toString(), getDateTime().format(format)
        );
    }
}