import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * This type of tasks need to be done by a certain deadline.
 */
public class Deadline extends Task {
    private LocalDate date;
    private LocalTime timing;

    /**
     * Class constructor.
     *
     * @param description Task description.
     * @param taskType Type of task: Deadline.
     * @param date Date of the deadline of task.
     * @param timing Timing of the deadline of task.
     */
    public Deadline(String description, char taskType, LocalDate date, LocalTime timing) {
        super(description, taskType);
        this.date = date;
        this.timing = timing;
    }

    /**
     * Returns the date of the deadline of the task in the correct format.
     *
     * @return Date of deadline of task.
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the timing of the deadline of the task in the correct format.
     *
     * @return Timing of deadline of task.
     */
    public String getTiming() {
        return timing.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * Returns all information about the task formatted in a single string.
     *
     * @return Information about task.
     */
    public String obtainTaskInfo() {
        String taskInfo = super.obtainTaskInfo();
        return taskInfo + " by " + this.getDate() + " " + this.getTiming();
    }
}
