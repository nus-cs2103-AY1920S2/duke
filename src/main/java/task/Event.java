package task;

public class Event extends Task {

    // TODO: Implement start/end time
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    protected String getTypeIcon() {
        return "[E]";
    }

    @Override
    protected TaskType getTaskType() {
        return TaskType.TASK_TYPE_EVENT;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }
}
