public class Task {

    private String taskname; //name of the given task
    private boolean done; //whether the task is done or not

    // constructor
    public Task(String taskname) {
        this.taskname = taskname;
        this.done = false;
    }

    /**
     * Function to set the task to done
     */
    public void setDone() {
        this.done = true;
    }


    /**
     * Function to get the task current status - done or not done
     * @return boolean to state whether is it done or not (true means done)
     */
    public boolean getDone() {
        return this.done;
    }


    /**
     * Function to get the task name
     * @return String name of the task
     */
    public String getTaskname() {
        return this.taskname;
    }
}
