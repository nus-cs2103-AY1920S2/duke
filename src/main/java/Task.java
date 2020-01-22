public class Task {
    private static String horizontalLine = "__________________________________________";
    protected String taskName;
    protected static int taskNumber = 1; // for Level 2
    protected int currentTaskNumber;
    protected boolean isDone; // for Level 3

    Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
        currentTaskNumber = taskNumber;
        this.taskNumber++;
    }

    public String getStatusIcon() {
        return (isDone ? "[Y]" : "[N]");
        // The symbols for the check marks and crosses do not seem to work and have been replaced by "Y" and "N" to
        // represent 'Yes' and 'No' respectively.

    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getTaskNumber() {
        return currentTaskNumber;
    }

    @Override
    public String toString() {
            return getStatusIcon() + " " + getTaskName();
    }
}
