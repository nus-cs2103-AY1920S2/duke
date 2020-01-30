package task;

/**
 * Specific type of task that contains description
 */
public class ToDo extends Task{

    /**
     * Constructor for the to do task
     * @param description is the detail of the task.
     */
    public ToDo(String description){
        super(description,"T");
    }


}
