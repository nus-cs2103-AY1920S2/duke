import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Deadline, which is a subclass of a Task. A Deadline includes
 * a byDate which represents the due date of the respective Deadline.
 */
public class Deadline extends Task {
    LocalDate byDate;
    String time24Hr;

    /**
     * Constructor for Deadline. Default isDone boolean value is false.
     *
     * @param description Description of deadline
     * @param date Date of the deadline
     * @param time Time of the deadline
     */
    public Deadline(String description, String date, String time) {
        super(description);
        this.byDate = LocalDate.parse(date);
        this.time24Hr = time;
    }

    /**
     * Another constructor for Deadline which allows explicit assigning of isDone.
     *
     * @param description Description of deadline
     * @param date Date of the deadline
     * @param time Time of the deadline
     * @param isDone True if deadline has been completed, false if not.
     */
    public Deadline(String description, String date, String time, boolean isDone) {
        super(description);
        this.byDate = LocalDate.parse(date);
        this.time24Hr = time;
        this.isDone = isDone;
    }

    /**
     * Returns a string representing time in 12-hour format eg. 11.59pm
     *
     * @param timeString String representing time in 24-hour format eg. 2359
     * @return Time in 12-hour format
     */
    public String formatTime12Hour(String timeString) {
        int time = Integer.parseInt(timeString);
        boolean isAM = time < 1200;
        if (!isAM && time >= 1300) {
            time = time - 1200;
        }
        int mins = time % 100;
        int hour = time / 100;
        return hour + "." + String.format("%02d", mins) + (isAM ? "am" : "pm");
    }


    @Override
    public String toString() {
        String checkbox = "[" + super.getStatusIcon() + "]";
        String dateFormat =  byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + checkbox + " " + super.toString()
                + " (by: " + dateFormat + ", " + formatTime12Hour(time24Hr) + ")";
    }
}