/**
 * Class that represents the task.
 * Contains taskName, done and taskType.
 */
public abstract class Task {

    public String taskname; //name of the given task
    public boolean done; //whether the task is done or not
    public String Tasktype;

    /**
     * Constructor.
     * @param taskname name of task.
     * @param Tasktype what type is it.
     */
    public Task(String taskname, String Tasktype) {
        this.taskname = taskname;
        this.done = false;
        this.Tasktype = Tasktype;
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
    public String getTaskname() {
        return this.taskname;
    }
}
