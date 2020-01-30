package duke.task;

public class ToDo extends Task {
    private final String bulletin = "[T]";

    private TaskType type;

    public ToDo(String name) {
        super(name);
        type = TaskType.toDo;
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    public String encoder() {
        return String.format("T:%s:%d\n", name, (isDone ? 1 : 0));
    }

    public TaskType getTaskType() {
        return type;
    }

    @Override
    public String toString() {
        if (super.isDone)
            return bulletin + " [" + doneSymbol + "] " + name;
        else
            return bulletin + " [" + notDoneYetSymbol + "] " + name;
    }
}
