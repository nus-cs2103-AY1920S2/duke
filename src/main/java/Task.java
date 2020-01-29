/**
 * Class that represents the task.
 * Contains taskName, done and taskType.
 */
public abstract class Task {

    private String taskName; //name of the given task
    private boolean isDone; //whether the task is done or not
    private String taskType;

    /**
     * Constructor.
     * @param taskName name of task.
     * @param taskType what type is it.
     */
    public Task(String taskName, String taskType) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Function to set the task to done.
     */
    public void setDone() {
        this.isDone = true;
    }


    /**
     * Function to get the task current status - done or not done.
     * @return boolean to state whether is it done or not (true means done).
     */
    public boolean getDone() {
        return this.isDone;
    }


    /**
     * Function to get the task name.
     * @return String name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Function to get the task type.
     * @return String type of task.
     */
    public String getTaskType() {
        return this.taskType;
    }
}
