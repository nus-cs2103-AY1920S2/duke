import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    String by;
    String i;
    LocalDate date;

    public Event(String event, String by) {
        super(event);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    public Event(String event, String by, String i) {
        super(event);
        this.i = i;
        this.by = by;
        if (i.equals("1")) {
            this.doneStatus = true;
        } else {
            this.doneStatus = false;
        }
        this.date = LocalDate.parse(by);
    }

    @Override
    public String save() {
        int myInt = doneStatus ? 1 : 0;
        return "E , " + myInt + " , " + taskName + " , " + by;
    }

    @Override
    public String toString() {
        String formattedBy = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (by: " + formattedBy + ")";
    }
}