package duke.task;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    public static final char ICON = 'D';

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
                getDateTime().format(Task.DATE_TIME_OUTPUT_FORMAT));
    }
    
    @Override
    public String toString() {
        return String.format("[%c]%s (by: %s)", getTaskIcon(),
                super.toString(),
                getDateTime().format(Task.DATE_TIME_OUTPUT_FORMAT));
    }
}
