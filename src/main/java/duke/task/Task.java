package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>TaskCommand Class</h1>
 * This command records the description of the task and indicate whether the programme is done or not.
 *
 * @author Eng Xuan En
 */
public class Task implements Comparable<Task> {
    protected String description;
    protected String type;
    protected boolean isDone;
    protected LocalDate period;
    private static final int EQUAL = 0;
    private static final int LOWER = 1;
    private static final int HIGHER = -1;

    /**
     * Class constructor of Task which takes in description in String format.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get period with the pattern format of "MMM d yyyy".
     *
     * @return date in "MMM d yyyy"
     */
    public String getPeriod() {
        return period.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Get status icon either a Y or N.
     *
     * @return status icon Y if is done, N if it is not done
     */
    public String getStatusIcon() {
        return isDone ? "Y" : "N";
    }

    /**
     * Get status of the task.
     *
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
     *
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the task type.
     *
     * @return type of the task
     */
    public String getType() {
        return type;
    }

    /**
     * Compare the tasks based on their date and time, if applicable. If same date and time, sort by
     * alphabetical order.
     *
     * @param other Another task to be compare to.
     * @return Determine which task is at the top.
     */
    @Override
    public int compareTo(Task other) {
        int result;

        result = sortingTask(this, other);

        if (result == EQUAL) {
            result = sortByAlphabeticalOrder(this, other);
        }

        return result;
    }

    /**
     * Sort todo at the bottom of the list and sort event and deadline based on their date and time.
     *
     * @param first Task to be compare.
     * @param second Another task to be compare.
     * @return Determine whether the first task is on top or below than the second task.
     */
    private int sortingTask(Task first, Task second) {
        int result;
        if (first.getType().equals("todo") && second.getType().equals("todo")) {
            result = EQUAL;
        } else if (first.getType().equals("todo")) {
            result = LOWER;
        } else if (second.getType().equals("todo")) {
            result = HIGHER;
        } else {
            result = first.getPeriod().compareTo(second.getPeriod());
        }

        return result;
    }

    /**
     * Sort the task by alphabetical order.
     *
     * @param first Task to be compare.
     * @param second Another task to be compare.
     * @return Determine whether the first task is on top or below than the second task.
     */
    private int sortByAlphabeticalOrder(Task first, Task second) {
        return first.getDescription().compareTo(second.getDescription());
    }

    /**
     * Return status icon and description of the task in string format.
     *
     * @return status icon and description in string format
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
