public class TodoTask extends Task{
    private final TaskType taskType = TaskType.TODO;

    private TodoTask(String taskName) {
        super(taskName, false);
    }

    public static TodoTask createTodoTask(String taskName) {
        return new TodoTask(taskName);
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }
}
