public class Task {
    private int done;
    private String taskName;
    private String type;

    Task() {
    }

    Task(String taskName) {

    }

    Task(String type, int done, String taskName) {
        this.type = type;
        this.done = done;
        this.taskName = taskName;
    }

    String getType() {
        return type;
    }

    void setDone() {
        done = 1;
    }
    void setTaskName(String s) {
        taskName = s;
    }
    int getDone() {
        return done;
    }
    String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        String yes_or_no = (this.done == 0) ? "[N]" : "[Y]";
        return yes_or_no + " " + this.taskName;
    }
}