package duketasks;

/**
 * Represents a Task object that is the parent class of Deadline, Event and Todo
 */
public class Task {
    protected boolean isDone;
    protected String isDoneString;
    protected String taskName;
    protected String taskCode;

    public Task(String taskName, String taskCode) {
        this.taskName = taskName;
        this.isDoneString = "X";
        this.isDone = false;
        this.taskCode = taskCode;
    }

    public void done() {
        isDone = true;
        isDoneString = "O";
    }

    public String getIsDoneString() {
        return isDoneString;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Return a formatted string to be used to save to file
     *
     * @return String of task details in the preferred save format
     */
    public String getSaveString() {
        String currEntry = String.format("%s|%s|%s", this.taskCode, this.getIsDoneString(), this.getTaskName());
        return currEntry;
    }


    /**
     * Used to return a formatted string of the general status of this task
     * that is used every time the details of the tasks needs to be printed
     *
     * @return String of task details
     */
    public String toString() {
        String output = String.format("[%s] %s", isDoneString, taskName);

        return output;
    }
}