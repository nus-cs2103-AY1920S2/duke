package duke;

import java.time.LocalDate;

/**
 * This Task class is to store the details of a Task.
 * @param description - to store the description of a Task
 * @param isDone - a flag to determine if a task is done or not done
 */
public class Task{
    protected String description;
    protected boolean isDone;
    protected String priorityLevel;
    protected String type;

    public Task(String description){
        this.description = description;
        this.isDone = false;
        this.priorityLevel = "Not-urgent";
        this.type = "";
    }
    public Task(String description, String status){
        this.description = description;
        this.isDone = false;
        this.priorityLevel = "Not-urgent";
        if (status.contains("1")){
            this.isDone = true;
        }
        this.type = "";
    }
     /**
     * This method is to validate the status of a task
     * @return tick symbol if its true, a X symbol if its false.
     */
    public String getStatus(){
        return (isDone ? "Y" : "X"); // return tick or X symbol
    }

    /**
     * Getter method for priorityLevel
     * @return priorityLevel
     */
    public String getPriority(){
        return this.priorityLevel;
    }

    /**
     * Setter method for priorityLevel to High
     */
    public void setPriorityHigh(){
        priorityLevel = "Urgent";
    }

    /**
     * Getter method is to return the description parameter as a string
     * @return description
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Setter method is flag isDone parameter to true (done)
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
    public LocalDate getWhen() {
        return null;
    }

    /**
     * This method is return an empty string
     * @return nothing
     */
    public String extraInfo(){
        return "";
    }
    public String getStringDate(){
        return "";
    }
    /**
     * This method is set the type of a Task e.g (T,E,D)
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * This method is return the type of a Task e.g (T,E,D)
     * @return type
     */
    public String getType(){
        return this.type;
    }
}
