/**
 * Class that represents the task.
 * Contains taskName, done and taskType.
 */
public abstract class Task {

    public String taskName; //name of the given task
    public boolean done; //whether the task is done or not
    public String taskType;

    /**
     * Constructor.
     * @param taskName name of task.
     * @param taskType what type is it.
     */
    public Task(String taskName, String taskType) {
        this.taskName = taskName;
        this.done = false;
        this.taskType = taskType;
    }

    /**
     * Function to set the task to done.
     */
    public void setDone() {
        this.done = true;
    }


    /**
     * Function to get the task current status - done or not done.
     * @return boolean to state whether is it done or not (true means done).
     */
    public boolean getDone() {
        return this.done;
    }


    /**
     * Function to get the task name.
     * @return String name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }
}
