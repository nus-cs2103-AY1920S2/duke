import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

<<<<<<< HEAD
    protected String type;
    protected String at;

    public Event(boolean isDone, String description, String at) {
        super(isDone, description);
        this.type = "E";
        this.at = at;
=======
    protected LocalDate date;
    protected LocalTime time;

    public Event(boolean isDone, String description, String atString) {
        super(isDone, description);

        //Date input format: yyyy-mm-dd HHmm
        String[] at = atString.split(" ");
        this.date = LocalDate.parse(at[0]);
        if (at.length > 1) {
            this.time = LocalTime.parse(at[1], DateTimeFormatter.ofPattern("HHmm"));
        } else {
            this.time = null;
        }
>>>>>>> branch-Level-8
    }

    @Override
    public String[] toDataTokens() {
        String isDoneString = String.valueOf(super.isDone);
        return new String[] {this.type, isDoneString, super.description, this.at};
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "[E]" + super.toString() + " (at: " + at + ")";
=======
        String dateTime = "";
        dateTime += this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (this.time != null) {
            dateTime += " " + this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
>>>>>>> branch-Level-8
    }
}