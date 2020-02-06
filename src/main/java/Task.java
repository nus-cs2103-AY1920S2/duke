import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class Task to represent a Task. A Task in our ChatBot
 * can be a Todo, Deadline, or Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate time;

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
     * Creates a new Task with {@code description} and specified {@code endTime}.
     * The Task is assumed to be uncompleted when created.
     *
     * @param description the description/details of our task
     */
    public Task(String description, String time) {
        // precondition: time in yyyy-mm-dd format
        this.description = description;
        this.isDone = false;
        this.time = LocalDate.parse(time);
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
    public boolean isTaskDone() { return isDone; }

    /**
     * gets the Date for our Task.
     *
     * @return the Date instance to represent a Date for our Task.
     */
    public LocalDate getDate() {
        return time;
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getTypeName() {
        return "Task";
    }

    /**
     * returns a String representation of a Task instance.
     *
     * @return String String representation
     */
    @Override
    public String toString() {
        String timeOptional = (time.equals("")) ? "" : ", " + time;
        return "[" + getStatusIcon() + "]" + " " + description + timeOptional;
    }

    /**
     * returns a String representation of a Task instance in our database.
     *
     * @return String a Task representation in our database
     */
    public String toStringFile() {
        int isDoneInt = (isDone) ? 1 : 0;
        String timeOptional = (time.equals(LocalDate.parse("2099-12-31"))) ? "" : ", " + getTime();
        return isDoneInt + " | " + description + timeOptional;
    }
}