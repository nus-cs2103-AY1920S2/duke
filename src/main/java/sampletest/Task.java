package sampletest;


/**
 * CS2103 Individual Project.
 * Task correspond to the actions depending on the command given by the user.
 * @author Wei Cheng
 */

public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     * @param description description of the task.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for the Task class.
     * when we are loading from the saved file.
     * @param description description of the task.
     * @param status status of the Task.
     */

    public Task(String description, String status) {
        this.description = description;
        this.isDone = status.equals("Yes");
    }

    /**
     * obtain the icon corresponding to the status of the Task.
     * @return an icon of tick when done or cross when it is not done.
     */

    public String getStatusIcon() {
        return (isDone ? "Done:)" : "Not Done:(");
    }

    /**
     * change an undone task to a completed task.
     * @return a new instance of Task by changing the status to Done.
     */

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * obtain a string representation of yes or no.
     * @return a String representation of Yes or No depending on the Status of the project.
     */

    public String checkDone() {

        return (isDone ? "Yes" : "No");
    }

    /**
     * Get the description of the string.
     * @return a String representation of the description of the task.
     */

    public String getDescription() {

        return this.description;
    }

    public boolean isUrgent() {
        return false;
    }

    public int getCompletionDate() {
        return Integer.parseInt(null);
    }

    @Override
    public int compareTo(Task task) {
        if (task.isUrgent() && this.isUrgent()) {
            return task.getCompletionDate() -  this.getCompletionDate();
        } else if (task.isUrgent() && !this.isUrgent()) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     *  The toString method.
     * @return String.
     */

    @Override
    public String toString() {
        assert this.description.length() > 0 : "The description of the Task is not valid";
        return " [T][" + this.getStatusIcon() + "]" + this.description;
    }
}
