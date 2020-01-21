public class Task {
    private static String horizontalLine = "__________________________________________";
    protected String taskName;
    protected static int taskNumber = 1; // for Level 2
    protected boolean isDone; // for Level 3

    Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskNumber++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
            return horizontalLine + "\n" + this.taskName + "\n" + horizontalLine;
    }
}
