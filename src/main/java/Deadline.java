class Deadline extends Task {
    String byDeadline;

    public Deadline(boolean taskCompleted, int taskNo, String taskName, String byDeadline) {
        super(taskCompleted, taskNo, taskName);
        this.byDeadline = "(by: " + byDeadline + ")";
    }

    @Override
    public String toString() {
        if (taskCompleted) {
            return taskNo + ".[D][✓] " + taskName + " " + byDeadline;
        } else {
            return taskNo + ".[D][✗] " + taskName + " " + byDeadline;
        }
    }
}
