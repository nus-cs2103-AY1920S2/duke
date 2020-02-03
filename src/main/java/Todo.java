/**
 * Represent a to-do task.
 * Extends the Task class
 */
public class Todo extends Task {
    /**
     * Constructor for to-do task.
     * @param description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Get the type of task.
     * D for Deadline
     * T for To-do
     * E for Event
     * @return the type of task
     */
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        String myword = "";
        myword = myword + "[" + this.getTaskType() + "]"
                + " [" + super.getStatusIcon() + "] " + super.description;
        return myword;
    }
}

