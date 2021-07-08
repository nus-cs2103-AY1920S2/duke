package dukebot.tasklist;

/**
 * The task types used.
 */
public enum TaskType {
    TODO("Todo"),
    DEADLINE("Deadline"),
    Event("Event");

    private final String taskType;
    TaskType(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return (this.taskType);
    }
}
