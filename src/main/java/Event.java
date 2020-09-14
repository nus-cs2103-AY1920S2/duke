import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This type of tasks have a specific start and end timing.
 */
public class Event extends Task {

    private LocalDate date;
    private LocalTime timing;

    /**
     * Class constructor.
     *
     * @param description Task description.
     * @param taskType Type of task: Event.
     * @param date Date of event.
     * @param timing Timing of event.
     */
    public Event(String description, String taskType, LocalDate date, LocalTime timing) {
        super(description, taskType);
        this.date = date;
        this.timing = timing;
    }

    /**
     * Class constructor to be used when loading data from hard disk during start-up.
     *
     * @param description Task description.
     * @param taskType Type of task: Event.
     * @param date Date of event.
     * @param timing Timing of event.
     * @param isDone Status of event, whether done or not.
     */
    public Event(String description, String taskType, LocalDate date, LocalTime timing, boolean isDone) {
        super(description, taskType, isDone);
        this.date = date;
        this.timing = timing;
    }

    /**
     * Returns the date of the event in the correct format.
     *
     * @return Date of event.
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the timing of the event in the correct format.
     *
     * @return Timing of event.
     */
    public String getTiming() {
        return timing.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * Returns all information about event formatted in a single string.
     *
     * @return Information about event.
     */
    public String obtainTaskInfo() {
        String taskInfo = super.obtainTaskInfo();
        return taskInfo + " on " + this.getDate() + " " + this.getTiming();
    }

    /**
     * Returns all information about the event formatted for storage in file.
     *
     * @return Information about event.
     */
    public String formatForFile() {
        String taskInfo = super.formatForFile();
        return taskInfo + "|" + this.getDate() + "|" + this.getTiming();
    }
}
