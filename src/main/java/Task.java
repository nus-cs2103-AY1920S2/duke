public interface Task {
    String getStatusIcon();
    void markAsDone();
    void markAsIncomplete();
    TaskType getTaskType();
    String getDescription();
}
