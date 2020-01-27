package duke.task;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Task implements Serializable {
    public static final char COMPLETED = 'X';
    public static final char PENDING = ' ';

    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String markAsCompleted() {
        if (isCompleted) {
            return String.format(
                    "'%s' has already been done",
                    getDescription()
            );
        } else {
            isCompleted = true;
            return String.format("Marked '%s' as done", getDescription());
        }
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