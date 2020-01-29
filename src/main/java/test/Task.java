package test;
/**
 * CS2103 Individual Project
 * @author Wei Cheng
 * Task correspond to the actions
 * depending on the command given by the user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for the Task class
     * when we are loading from the saved file.
     * @param description description of the task
     * @param status status of the Task
     */
    public Task(String description, String status){
        this.description = description;
        this.isDone = status.equals("Yes");
    }

    /**
     * @return an icon of tick when done
     *      * or cross when it is not done
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * @return a new instance of Task
     * by changing the status to Done
     */
    public Task markAsDone(){
        this.isDone = true;
        return this;
    }

    /**
     * @return a String representation of Yes or No
     * depending on the Status of the project
     */
    public String checkDone(){

        return(isDone ? "Yes" : "No");
    }

    /**
     *
     * @return a String representation of the description of the task
     */
    public String getDescription(){

        return this.description;
    }

    /**
     *  The toString method
     * @return String
     */
    @Override
    public String toString(){
            return " [T][" + this.getStatusIcon() + "]" + this.description;
    }
}
