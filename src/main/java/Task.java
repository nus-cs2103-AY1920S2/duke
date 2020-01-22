public class Task {
    public static char COMPLETED = 'X';
    public static char PENDING = ' ';

    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void markAsCompleted() {
        isCompleted = true;
    }

    public char getStatusIcon() {
        return isCompleted ? Task.COMPLETED : Task.PENDING;
    }

    @Override
    public String toString() {
        return description;
    }
}