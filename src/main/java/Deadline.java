import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    public String DateTime;

    public Deadline(String taskname, String DateTime) {
        super(taskname, "D");
        this.DateTime = DateTime;
    }

    @Override
    public String toString() {
        String dateToBePrinted;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(this.DateTime, formatter);
            dateToBePrinted = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            dateToBePrinted = this.DateTime; //whatever error inputs given by user will be just converted to string format
        }
        String message = "";
        if (this.done) {
            message += "[" + this.Tasktype +"]" + "[" + "\u2713" + "] " + this.taskname + " (by: " + dateToBePrinted + ")";
        } else {
            message +=  "[" + this.Tasktype +"]" + "[" + "\u2718" + "] " + this.taskname + " (by: " + dateToBePrinted + ")";
        }
        return message;
    }
}
