public class Task {
    public static char COMPLETED = 'X';
    public static char PENDING = ' ';

    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String markAsCompleted() {
        isCompleted = true;
        return String.format("Marked '%s' as done", getDescription());
    }

    public String getDescription() {
        return description;
    }

    public char getStatusIcon() {
        return isCompleted ? Task.COMPLETED : Task.PENDING;
    }

    @Override
    public String toString() {
        return String.format(
                "[%c] %s",
                getStatusIcon(), getDescription()
        );
    }
}