/**
 * Class that represents the task.
 * Contains taskName, done and taskType.
 */
public abstract class Task {

    private String taskName; //name of the given task
    private boolean isDone; //whether the task is done or not
    private String taskType;
    private int priority; // 1 - low, 2 - medium, 3 - high

    /**
     * Constructor.
     * @param taskName name of task.
     * @param taskType what type is it.
     */
    public Task(String taskName, String taskType) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskType = taskType;
        this.priority = 2;
    }

    /**
     * Constructor.
     * @param taskName name of task.
     * @param taskType what type is it.
     * @param priority what priority does the task hold.
     */
    public Task(String taskName, String taskType, int priority) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.priority = priority;
        this.isDone = false;
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

    public int getPriority() {
        return this.priority;
    }

    /**
     * Set the priority of task.
     * @param priority priority to be set.
     * @return if the priority has been successfully changed.
     */
    public boolean setPriority(int priority) {
        if (this.priority == priority) {
            return false;
        } else {
            this.priority = priority;
            return true;
        }
    }

    /**
     * Returns String representation of priority.
     * @return String representation.
     */
    public String getPriorityString() {
        if (this.priority == 1) {
            return "LOW";
        } else if (this.priority == 2) {
            return "MEDIUM";
        } else {
            return "HIGH";
        }
    }
}
