package duke.commons;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String type;
    protected String atString;
    protected LocalDate date;
    protected LocalTime time;

    public Event(String type, boolean isDone, String description, String atString) {
        super(type, isDone, description);
        this.atString = atString;

        //Date input format: yyyy-mm-dd HHmm
        String[] at = atString.split(" ");
        this.date = LocalDate.parse(at[0]);
        if (at.length > 1) {
            this.time = LocalTime.parse(at[1], DateTimeFormatter.ofPattern("HHmm"));
        } else {
            this.time = null;
        }
    }

    public String getTypeSymbol() {
        return "E";
    }

    @Override
    public String[] toDataTokens() {
        String isDoneString = String.valueOf(super.isDone);
        return new String[] {getTypeSymbol(), isDoneString, super.description, this.atString};
    }

    @Override
    public String toString() {
        String dateTime = "";
        dateTime += this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (this.time != null) {
            dateTime += " " + this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
        }
        return "[" + getTypeSymbol() + "]" + super.toString() + " (at: " + dateTime + ")";
    }
}