public interface Task {
    String getStatusIcon();
    void markAsDone();
    void markAsIncomplete();
    String getDescription();
}
