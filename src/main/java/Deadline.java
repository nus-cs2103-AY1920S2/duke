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

    public Deadline(String description, String date, String time, boolean isDone) {
        super(description);
        this.byDate = LocalDate.parse(date);
        this.time24Hr = time;
        this.isDone = isDone;
    }

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
        return "[D]" + checkbox + " " + super.toString() + " (by: " + dateFormat + ", " + formatTime12Hour(time24Hr) + ")";
    }
}