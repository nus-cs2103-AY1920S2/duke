import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate at;
    protected LocalTime atTime;

    public Event(String description, String at) throws DukeException{
        super(description);
        try {
            String[] temp = at.split(" ");
            this.at = LocalDate.parse(temp[0]);
            if (temp.length == 2) {
                this.atTime = LocalTime.parse(temp[1]);
            } else {
                this.atTime = null;
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Format is yyyy-mm-dd HH:mm. Time is optional.");
        }
    }

    @Override
    public String toSaveName() {
        return "E" + super.toSaveName() + " | " + this.at +
                (atTime != null? " " + atTime : "") + "\n";
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[MMM d yyyy][h:mma]");
        return "[E]" + super.toString() + " (by: " +  at.format(formatter) +
                (atTime != null ? " " + atTime.format(formatter) : "") + ")";
    }
}
