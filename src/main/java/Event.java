import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    LocalDate atDate;
    String time24Hr;

    public Event(String description, String date, String time) {
        super(description);
        this.atDate = LocalDate.parse(date);
        this.time24Hr = time;
    }

    public Event(String description, String date, String time, boolean done) {
        super(description, done);
        this.atDate = LocalDate.parse(date);
        this.time24Hr = time;
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
        String dateFormat = atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + checkbox + " " + super.toString() + " (at: " + dateFormat + ", " + formatTime12Hour(time24Hr) + ")";
    }
}