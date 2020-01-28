import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date date;

    public Deadline(String taskTitle, Date date) {
        super(taskTitle);
        this.date = date;
    }

    @Override
    public String toString() {
        String datePattern = "MMM d yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        return "[D]" + super.toString() + " (by: " + simpleDateFormat.format(date) + ")";
    }
}
