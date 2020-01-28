import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Deadline extends Task {

    String by;
    LocalDate date;
    LocalTime time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        String[] arr = by.split(" ");
        this.date = LocalDate.parse(arr[0]);

        if(arr.length > 1) {
            char[] charArr = arr[1].toCharArray();
            String hour = "" + charArr[0] + charArr[1];
            String mins = "" + charArr[2] + charArr[3];
            this.time = LocalTime.parse(hour + ":" + mins);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + time.format(DateTimeFormatter.ofPattern("hhmma")) + ")";
    }
}
