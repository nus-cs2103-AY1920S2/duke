public class DeadlineTask extends Task{
    private String due;
    private final TaskType taskType = TaskType.TODO;

    private DeadlineTask(String taskName, String due) {
        super(taskName, false);
        this.due = due;
    }

    public static DeadlineTask createDeadlineTask(String taskName, String due) {
        return new DeadlineTask(taskName, due);
    }

    @Override
    public String toString() {
        return super.toString().concat(String.format(" by %s", due));
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }
}
