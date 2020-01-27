package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class DeadlineTask extends Task {
    public static final char ICON = 'D';

    private LocalDateTime by;

    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public LocalDateTime getDateTime() {
        return by;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter
                                    .ofPattern("EE, dd MMM yyyy, HH:mm");
        return String.format(
                "[%c]%s (by: %s)",
                DeadlineTask.ICON, super.toString(),
                getDateTime().format(format)
        );
    }
}