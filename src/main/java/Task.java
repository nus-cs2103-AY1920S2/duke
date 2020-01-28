public class Task {
    public boolean doneStatus;
    public String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
        this.doneStatus = false;
    }

    public void mark() {
        this.doneStatus = !this.doneStatus;
    }

    public String save() {
        return null;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        String symbol;
        if (doneStatus) {
            symbol = "  complete  ";
        } else {
            symbol = " incomplete ";
        }
        return "[" + symbol + "] " + taskName;
    }
}
