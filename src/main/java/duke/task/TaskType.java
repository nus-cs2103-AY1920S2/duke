package duke.task;

public enum TaskType {
    E(Event.class),
    D(Deadline.class),
    T(Todo.class);

    private Class<?> taskType;

    TaskType(Class<?> taskType) {
        this.taskType = taskType;
    }

    public Class<?> getTaskType() {
        return this.taskType;
    }
}
