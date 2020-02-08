package duke.task;

public abstract class Task {
    public static final char COMPLETED = 'X';
    public static final char PENDING = ' ';
    public static final char DELIMITER = '|';
    public static final String ALREADY_DONE_MESSAGE_TEMPLATE
            = "'%s' has already been done";
    public static final String MARK_AS_DONE_MESSAGE_TEMPLATE
            = "Marked '%s' as done";

    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String markAsCompleted() {
        if (isCompleted) {
            return String.format(Task.ALREADY_DONE_MESSAGE_TEMPLATE,
                    getDescription());
        } else {
            isCompleted = true;
            return String.format(Task.MARK_AS_DONE_MESSAGE_TEMPLATE,
                    getDescription());
        }
    }

    public abstract char getTaskIcon();

    public String getDescription() {
        return description;
    }

    public char getStatusIcon() {
        return isCompleted ? Task.COMPLETED : Task.PENDING;
    }

    public String toStringDelimited() {
        return String.format("%c %c %s", getStatusIcon(), Task.DELIMITER,
                getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", getStatusIcon(), getDescription());
    }
}
