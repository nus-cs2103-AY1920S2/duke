public class EventTask extends Task {
    private String due;
    private final TaskType taskType = TaskType.TODO;

    private EventTask(String taskName, String due) {
        super(taskName, false);
        this.due = due;
    }

    public static EventTask createEventTask(String taskName, String due) {
        return new EventTask(taskName, due);
    }

    @Override
    public String toString() {
        return super.toString().concat(String.format(" at %s", due));
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }
}
