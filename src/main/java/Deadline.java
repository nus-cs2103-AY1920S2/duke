import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    LocalDate byDate;
    String time24Hr;

    public Deadline(String description, String date, String time) {
        super(description);
        this.byDate = LocalDate.parse(date);
        this.time24Hr = time;
    }

    public String formatTime12Hour(String timeString) {
        int time = Integer.parseInt(timeString);
        boolean isAM = time < 1200;
        if (!isAM && time >= 1300) {
            time = time - 1200;
        }
        time = time / 100;
        return time + (isAM ? "am" : "pm");
    }

    public Deadline(String description, String by, boolean done) {
        super(description, done);
        this.by = by;
    }

    @Override
    public String toString() {
        String checkbox = "[" + super.getStatusIcon() + "]";
        String dateFormat =  byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + checkbox + " " + super.toString() + " (by: " + dateFormat + ", " + formatTime12Hour(time24Hr) + ")";
    }
}