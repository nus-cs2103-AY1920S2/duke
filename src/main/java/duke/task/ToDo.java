package duke.task;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
        this.taskType = "T";
    }

    public ToDo(String taskName, boolean isDone) {
        super(taskName.trim(), isDone);
        this.taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}