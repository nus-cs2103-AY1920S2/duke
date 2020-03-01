import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class Task to represent a Task. A Task in our ChatBot
 * can be a Todo, Deadline, or Event.
 */
abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with {@code description}.
     * The Task is assumed to be uncompleted when created.
     *
     * @param description the description/details of our task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the description of our Task.
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks our Task as completed.
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the status icon of our Task.
     *
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * checks if our Task is completed.
     *
     * @return boolean to indicate whether our Task is completed.
     *
     */
    public boolean isTaskDone() {
        return isDone;
    }

    public abstract String getTypeName();

    public abstract String getTimeOutput();

    public abstract String getTimeToDatabase();

    public abstract LocalDate getDate();

    /**
     * returns a String representation of a Task instance.
     *
     * @return String String representation
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

    /**
     * returns a String representation of a Task instance in our database.
     *
     * @return String a Task representation in our database
     */
    public String toStringFile() {
        int isDoneInt = (isDone) ? 1 : 0;
        return isDoneInt + " | " + description;
    }
}