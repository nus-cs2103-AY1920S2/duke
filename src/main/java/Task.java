/**
 * The Task class is a parent class for the different types of tasks that the user
 * input will generate.
 */
class Task {
    private static String horizontalLine = "__________________________________________";
    protected String taskName;
    protected static int taskNumber = 1; // for Level 2
    protected int currentTaskNumber;
    protected boolean isDone; // for Level 3

    /**
     * Constructor for a Task takes in a String task name that is specified by the user.
     *
     * @param taskName The String input by the user.
     */
    Task(String taskName) {
        this.isDone = false;
        this.taskName = taskName;
        currentTaskNumber = taskNumber;
        this.taskNumber++;
    }

    /**
     * This method serves to get the status of a specific task to check if the task is done or not.
     *
      * @return Returns [Y] if the task is done and [N] if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[Y]" : "[N]");
        // The symbols for the check marks and crosses do not seem to work and have been replaced by "Y" and "N" to
        // represent 'Yes' and 'No' respectively.

    }

    /**
     * This method changes the status of a specified task to be done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * This method returns the name of a specified task.
     *
     * @return A String of the name of the task.
     */
    public String getTaskName() {
        return taskName.trim();
    }

    /**
     * This task returns the task number of the specified task.
     *
     * @return A number of type int.
     */
    public int getTaskNumber() {
        return currentTaskNumber;
    }

    /**
     * The toString method of a Task object returns the status of the task and the name of the task.
     *
     * @return A String containing details of a task object.
     */
    @Override
    public String toString() {
            return getStatusIcon() + " " + getTaskName() + " ";
    }
}
