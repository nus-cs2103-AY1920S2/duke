package duke.task;

/**
 * Use to represent functions required for a type of task.
 */
public interface Task {
    String getStatusIcon();

    void markAsDone();

    void markAsIncomplete();

    String getDescription();

    String stringToSaveToDisk();

    boolean getTaskCompletionStatus();
}
