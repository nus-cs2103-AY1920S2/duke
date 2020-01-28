import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    public static String datePattern = "MMM d yyyy";
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Deadline.datePattern);
    protected Date date;

    public static Date parseDate(String inputDate) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(pattern);
        return inputDateFormat.parse(inputDate);
    }

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