import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String time;
    LocalDate localDate;

    public Event(String description, String time) throws GrapieExceptions {//String time) {
        super(description);

        time = time.trim();

        String[] splittedToCheckIfValid = time.split("-");
        if (splittedToCheckIfValid.length == 3 && time.length() == 10) {
            localDate = LocalDate.parse(time);
        } else {
            throw new GrapieExceptions("OOPS!!! Please format your date like this: YYYY-MM-DD TTTT");
        }
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
