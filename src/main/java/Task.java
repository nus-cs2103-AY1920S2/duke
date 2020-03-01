/**
 * CS2103 Individual Project.
 * The Task class is a subclass for all todos, deadlines, and events. It contains a
 * String description of the task and a boolean to determine if the task is completed.
 * @author Chiang Shun De
 */

public class Task {

    protected String description;
    protected boolean isDone;
    protected String binary;

    /**
     * Constructor method to create an instance of Task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.binary = "0";
    }

    /**
     * A getter method to obtain the description of the task.
     * @return A String representation of the task description.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * A getter method to obtain a icon corresponding to whether the task is completed
     * or not.
     * @return A tick if the task is completed and a cross if the task is not completed.
     */
    public String getIcon() {
        if (isDone) {
            return "[✓]";
        } else {
            return "[✘]";
        }
    }



    /**
     * A method to mark the task as completed.
     */
    public void markDone() {
        this.isDone = true;
        this.binary = "1";
    }

    /**
     * A method to save the task in a readable format, with a binary representing
     * whether the task has been completed or not.
     * @return A String representation of the task in a readable format
     */
    public String saveTask(){
        return "T | " + this.binary + " | " + this.description;
    }

    /**
     * Prints the completion icon of the task, and the task description.
     * @return A String which contains the completion icon and the task description.
     */
    @Override
    public String toString(){
        return this.getIcon() + " " + this.description;
    }
}
