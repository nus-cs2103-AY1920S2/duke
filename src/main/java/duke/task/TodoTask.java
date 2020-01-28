package duke.task;

public class TodoTask extends Task {
    public static final char ICON = 'T';

    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public char getTaskIcon() {
        return TodoTask.ICON;
    }

    @Override
    public String toStringDelimited() {
        return String.format(
                "%c %c %s",
                getTaskIcon(), Task.DELIMITER, super.toStringDelimited()
        );
    }

    @Override
    public String toString() {
        return String.format("[%c]%s", getTaskIcon(), super.toString());
    }
}