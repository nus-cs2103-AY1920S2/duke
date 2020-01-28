import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a <code>Deadline</code>, which is a subclass of a <code>Task</code>. A <code>Deadline</code> includes
 * a byDate which represents the due date of the respective <code>Deadline</code>.
 */
public class Deadline extends Task {
    LocalDate byDate;
    String time24Hr;

    public Deadline(String description, String date, String time) {
        super(description);
        this.byDate = LocalDate.parse(date);
        this.time24Hr = time;
    }

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