import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

public class Event extends Task {

    protected LocalDate date;
    protected LocalTime start;
    protected LocalTime end;

    public Event (String description, LocalDate date, LocalTime start, LocalTime end) {
        super (description);
        this.date = date;
        this.start = start;
        this.end = end;
    }

    @Override
    public String saveFile() {
        if (this.status.equals("Done")) {
            return  "E|1||" + this.description + "|||" + this.date + " "
                    + this.start + "-" + this.end;
        } else {
            return  "E|0||" + this.description + "|||" + this.date + " "
                    + this.start + "-" + this.end;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy, "))
                + this.start.format(DateTimeFormatter.ofPattern ("hh:mm a")) + " - "
                + this.end.format (DateTimeFormatter.ofPattern ("hh:mm a")) + ")";
    }
}

