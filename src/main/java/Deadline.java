import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private static String datePattern = "MMM d yyyy";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
    protected Date date;

    public Deadline(String taskTitle, Date date) {
        super(taskTitle);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + simpleDateFormat.format(date) + ")";
    }

    public String getDeadline() {
        return simpleDateFormat.format(date);
    }
}