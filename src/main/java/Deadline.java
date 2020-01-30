import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task{
    Date time;

    public Deadline(String description, String time) {
        super(description);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm");

        this.time = format.parse(time, new ParsePosition(0));
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy HH:mm");

        return "[D]" + super.toString() + " (by: " + format.format(time) + ")";
    }
}
