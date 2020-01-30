package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    public static final char ICON = 'D';
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMAT
            = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static final DateTimeFormatter DATE_TIME_OUTPUT_FORMAT
            = DateTimeFormatter.ofPattern("EE, dd MMM yyyy, HH:mm");

    private LocalDateTime by;

    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public DeadlineTask(String description, LocalDateTime by,
            boolean isCompleted) {
        super(description, isCompleted);
        this.by = by;
    }

    public LocalDateTime getDateTime() {
        return by;
    }

    @Override
    public char getTaskIcon() {
        return DeadlineTask.ICON;
    }

    @Override
    public String toStringDelimited() {
        return String.format("%c %c %s %c %s", getTaskIcon(), Task.DELIMITER,
                super.toStringDelimited(), Task.DELIMITER,
                getDateTime().format(DeadlineTask.DATE_TIME_OUTPUT_FORMAT));
    }
    
    @Override
    public String toString() {
        return String.format("[%c]%s (by: %s)", getTaskIcon(),
                super.toString(),
                getDateTime().format(DeadlineTask.DATE_TIME_OUTPUT_FORMAT));
    }
}