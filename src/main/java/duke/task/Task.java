package duke.task;
/**
 * Am abstract representation of a task issued to the Duke program.
 */
public abstract class Task {

    private static final String TO_DO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    static int size = 0;
    protected String description;
    private boolean isDone = false;
    protected String type = "task";

    /**
     * Outputs a String representation of the Task object.
     *
     * @return the String representation.
     */
    @Override
    public String toString() {
        return String.format("%s%s", this.description, this.isDone ? " (Completed)" : " (Uncompleted)");
    }

    /**
     * Returns the type of the Task object.
     *
     * @return type of the Task object with type String.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the description of the Task object
     *
     * @return description of the Task object with type String.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the Task object
     *
     * @return status of the Task object with type String.
     */
    public String getStatus() {
        return this.isDone ? "T" : "F";
    }

    /**
     * Sets the isDone field to true. If isDone is already true, throws an exception.
     *
     * @throws Exception when value of isDone field is already true
     */
    public void setDone() throws Exception {
        if (isDone) {
            throw new Exception(" You might have been mistaken. This task has already been completed.");
        }
        isDone = true;
    }

    /**
     *
     */
    public void setUndone() {
        assert this.isDone;
        this.isDone = false;
    }

    /**
     * Generates a Task object based on the input. The Task object can be a DeadlineTask, an EventTask, or a TodoTask.
     *
     * @param inputAsArray a String array representing the input to create the Task object.
     * @return a Task object.
     * @throws Exception if the task type is invalid.
     */
    public static Task generateTask(String[] inputAsArray) throws Exception {
        String type = inputAsArray[0];
        switch (type.toLowerCase()) {
        case TO_DO: {
            return new TodoTask(inputAsArray);
        }
        case EVENT: {
            return new EventTask(inputAsArray);
        }
        case DEADLINE: {
            return new DeadlineTask(inputAsArray);
        }
        default:
            throw new Exception(" You might have not chosen a valid task type or command!");
        }


    }

}


