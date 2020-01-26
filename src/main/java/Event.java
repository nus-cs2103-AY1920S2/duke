class Event extends Task {
    String atEvent;

    public Event(boolean taskCompleted, int taskNo, String taskName, String atEvent) {
        super(taskCompleted, taskNo, taskName);
        this.atEvent = "(at: " + atEvent + ")";
    }

    @Override
    public String toString() {
        if (taskCompleted) {
            return taskNo + ".[E][✓] " + taskName + " " + atEvent;
        } else {
            return taskNo + ".[E][✗] " + taskName + " " + atEvent;
        }
    }
}
