package duke.tasks;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    public String getType() {
        return "[T]";
    }

    @Override
    public boolean equals(Object task) {
        if (task instanceof ToDo) {
            return ((ToDo) task).getTaskName().equals(this.getTaskName());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
