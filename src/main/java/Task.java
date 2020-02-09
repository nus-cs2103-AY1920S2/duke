public class Task {

    protected String description;
    protected boolean isCompleted;
    protected String tasktype;

    /**
     * Constructor method for the Task class.
     * @param description Description of the specific Task.
     * @param tasktype Type of task: Can be a Todo, Event, or a Deadline.
     */
    public Task (String description, String tasktype) {
        this.description = description;
        this.isCompleted = false;
        this.tasktype = tasktype;
    }

    // GETTER METHODS ================================================================================
    /**
     * Getter method to return description of the Task.
     * @return Description of Task.
     */
    public String getDescription() {
        assert this.description.length() != 0: "Your mission parameters have been wiped!";
        return this.description;
    }

    /**
     * Getter method to return the type of Task (could be Todo, Event, or Deadline).
     * @return A string which describes the type of task
     */
    public String getTaskType() {
        return this.tasktype;
    }

    /**
     * Getter method to return completion status of the Task as a boolean variable.
     * @return Current completion status of the Task in the form of boolean variable.
     */
    public boolean getCompletionStatus() {
        return this.isCompleted;
    }

    /**
     * Getter method to return completion status of the Task as a tick/cross symbol with brackets.
     * @return Current completion status of the Task in the form of tick or cross symbols.
     */
    public String getCompletionStatusAsString() {
        String symbol = this.isCompleted ? "\u2713" : "\u2718";
        return ("[" + symbol + "]");
    }

    /**
     * Getter method to return date of the Task. Will be overridden by the Event and Deadline classes.
     * @return Empty String
     */
    public String getDate() {
        return "";
    }

    /**
     * Getter method to return date of the Task in a format suitable for writing to the "memory"/text file.
     * Will be overriden by the Event and Deadline classes.
     * @return Empty String
     */
    public String getDateForWritingToFile() {
        return "";
    }

    // UPDATE METHODS ================================================================================

    /**
     * Updates the progress status of the Task.
     * @param update Current boolean status of whether or not the Task has been completed.
     */
    public void updateIsCompleted(boolean update) {
        this.isCompleted = update;
    }

}
