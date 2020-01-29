package duke.task;

public class Todo extends Task {

    /**
     * Constructor for a Todo.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    protected String getTypeIcon() {
        return "[T]";
    }

    @Override
    protected TaskType getTaskType() {
        return TaskType.TASK_TYPE_TODO;
    }
}
