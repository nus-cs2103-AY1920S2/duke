import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    public String DateTime;
    public LocalDateTime date;

    public Deadline(String taskname, String DateTime) {
        super(taskname, "D");
        this.DateTime = DateTime;
        this.constructDate(DateTime);
    }

    public void constructDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
        this.date = LocalDateTime.parse(this.DateTime, formatter);
    }

    @Override
    public String toString() {
        String message = "";
        String dateToBePrinted = this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (this.done) {
            message += "[" + this.Tasktype +"]" + "[" + "\u2713" + "] " + this.taskname + " (by: " + dateToBePrinted + ")";
        } else {
            message +=  "[" + this.Tasktype +"]" + "[" + "\u2718" + "] " + this.taskname + " (by: " + dateToBePrinted + ")";
        }
        return message;
    }

    public static void validDate(String date) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
        LocalDateTime d = LocalDateTime.parse(date, formatter);
    }
}
