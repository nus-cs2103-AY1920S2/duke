import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate localDate;
    LocalTime localTime;

    String time;
    boolean haveTime;

    /**
     * Constructor to create Deadline object.
     *
     * @param description The description for deadline.
     * @param time        The time for the deadline task.
     * @throws GrapieExceptions Throws error for incorrect formatting.
     */
    public Deadline(String description, String time) throws GrapieExceptions {
        super(description);
        this.time = time;

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
        return "[D]" + super.toString() + " (by: " + localDate.format(DateTimeFormatter.ofPattern("MMM d " +
                "yyyy")) + ")";
    }
}





