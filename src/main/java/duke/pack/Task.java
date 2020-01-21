package duke.pack;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * creates a task with the specified description
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * gets the status of task, whether it is done or not
     * @return A string that shows the corresponding tick/cross symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * gets description of task
     * @return a string that is the task's description
     */
    public String getDescription() {
        return description; //return task description
    }

    /**
     * marks the task as done
     */
    public void markAsDone() {
        isDone = true;

        System.out.println("    ------------------------------------------------------------");
        System.out.println("    Fantastic job! I have marked this task as done:");
        System.out.println("      " +  description + " [" + getStatusIcon() + "] ");
        System.out.println("    ------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return (description + " [" + getStatusIcon() + "]");
    }
}
