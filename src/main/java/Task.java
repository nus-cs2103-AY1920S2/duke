/**
 * This Task class is to store the details of a Task.
 * @param description - to store the description of a Task
 * @param isDone - a flag to determine if a task is done or not done
 */
public class Task{
    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
     /**
     * This method is to validate the status of a task
     * @return tick symbol if its true, a X symbol if its false.
     */
    public String getStatus(){
        return (isDone ? "Tick" : "Untick"); // return tick or X symbol
    }

    /**
     * This method is to return the description parameter as a string
     * @return description
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * This method is flag isDone parameter to true (done)
     * @return nothing
     */
    public void markDone(){
        this.isDone = true;
    }
    /**
     * This method is flag isDone parameter to true (done)
     * @return nothing
     */
    public String toString(){
        return "[" + this.getStatus() + "] " + this.getDescription();
    }

    /**
     * This method is return an empty string
     * @return nothing
     */
    public String getWhen() {
        return "";
    }

    /**
     * This method is return an empty string
     * @return nothing
     */
    public String extraInfo(){
        return "";
    }
}
