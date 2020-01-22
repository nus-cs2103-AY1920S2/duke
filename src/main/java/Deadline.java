import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setDateTime() {
        String[] b = this.by.split(" ");
        if (b.length > 1) {
            this.date = LocalDate.parse(b[0]);
            this.time = LocalTime.parse(b[1]);
        } else {
            this.date = LocalDate.parse(this.by);
            this.time = null;
        }
    }

    public String dateToString() {
        setDateTime();
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String timeToString() {
        setDateTime();
        return this.time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    @Override
    public String toString() {
        //return "[D]" + super.toString() + "(by: " + by + ")";
        String[] b = this.by.split(" ");
        if (b.length > 1) {
            return "[D]" + super.toString() + "(by: " + dateToString() + " " + timeToString()+ ")";
        } else {
            return "[D]" + super.toString() + "(by: " + dateToString() + ")";
        }
    }
}
