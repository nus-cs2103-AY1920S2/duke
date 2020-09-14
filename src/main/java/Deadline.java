import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    public Deadline(String description, String taskType, LocalDate date, LocalTime timing) {
        super(description, taskType);
        this.date = date;
        this.timing = timing;
    }

    /**
     * Class constructor to be used when loading data from hard disk during start-up.
     *
     * @param description Task description.
     * @param taskType Type of task: Deadline.
     * @param date Date of deadline of task.
     * @param timing Timing of deadline of task.
     * @param isDone Status of task, whether done or not.
     */
    public Deadline(String description, String taskType, LocalDate date, LocalTime timing, boolean isDone) {
        super(description, taskType, isDone);
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
     * @return Information about deadline task.
     */
    public String obtainTaskInfo() {
        String taskInfo = super.obtainTaskInfo();
        return taskInfo + " by " + this.getDate() + " " + this.getTiming();
    }

    /**
     * Returns all information about the deadline task formatted for storage in file.
     *
     * @return Information about deadline task.
     */
    public String formatForFile() {
        String taskInfo = super.formatForFile();
        return taskInfo + "|" + this.getDate() + "|" + this.getTiming();
    }
}
