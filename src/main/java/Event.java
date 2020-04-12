import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class Event to represent a Event Task. An Event
 * consists of a description and a time period.
 */
public class Event extends Task {
    protected LocalDate startTime; // this will be startTime or endTime depending on the underlying class.

    /**
     * Creates a new Event with {@code description} and specified {@code time}.
     *
     * @param description the description/details of our task
     * @param startTime the time that the event happens
     */
    public Event(String description, String startTime) {
        super(description);
        this.startTime = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Retrieves our class type to differentiate Events among the generic Task instances.
     *
     * @return "E" the initial for our Event Class
     */
    @Override
    public String getTypeName() {
        return "E";
    }

    @Override
    public String getTimeOutput() {
        return startTime.format(DateTimeFormatter.ofPattern("d MMM YYYY"));
    }

    @Override
    public String getTimeToDatabase() {
        return startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public LocalDate getDate() {
        return startTime;
    }


    /**
     * returns a String representation of an Event instance.
     *
     * @return String String representation
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " " + startTime.format(DateTimeFormatter.ofPattern("d MMM YYYY"));
    }

    /**
     * returns a String representation of an Event instance in our database.
     *
     * @return String an Event representation in our database
     */
    @Override
    public String toStringFile() {
        return "E" + " | " + super.toStringFile();
    }
}
