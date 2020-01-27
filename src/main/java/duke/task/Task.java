package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>TaskCommand Class</h1>
 * This command records the description of the task and indicate whether the programme is done or not.
 *
 * @author  Eng Xuan En
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected LocalDate period;

    /**
     * Class constructor of Task which takes in description in String format.
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get period with the pattern format of "MMM d yyyy".
     * @return date in "MMM d yyyy"
     */
    public String getPeriod() {
        return period.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Get status icon either a tick or cross.
     * @return status icon tick if is done, cross if it is not done
     */
    public String getStatusIcon() {
        return isDone? "\u2713" : "\u2718";
    }

    /**
     * Get status of the task.
     * @return task status
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Set done status to true.
     */
    public void setStatusDone() {
        this.isDone = true;
    }

    /**
     * Set done status to false.
     */
    public void setStatusNotDone() {
        this.isDone = false;
    }

    /**
     * Get the description of the task.
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the task type.
     * @return type of the task
     */
    public String getType() {
        return type;
    }

    /**
     * Return status icon and description of the task in string format.
     * @return status icon and description in string format
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
