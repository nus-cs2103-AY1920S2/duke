package task;

public class Task {
    /**
     * description params -- to store the task description
     * isDone params -- to record down the status of task
     */
    private String description;
    private boolean isDone;
    private String tag;

    public Task() {
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * this method return the description of the task
     *
     * @return params description
     */
    public String getDescription() {
        return description;
    }

    /**
     * this method set isDone params to true;
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * this method return status of the task depending on isDone boolean variable
     *
     * @return params isDone
     */
    public String getStatusIcon() {
        return (isDone ? "Done" : "NoDone");
    }

    public String getDate(){
        return "there is no date in task ";
    }

    public void setTag(String tag){
        this.tag = "#" + tag;
    }

    public boolean getDoneStatus(){
        return isDone;
    }


    /**
     * this method return the string of the task description and status
     *
     * @return params statusIcon and description
     */
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription() + "  tag:"+this.tag;
    }
}
