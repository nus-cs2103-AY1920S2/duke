/**
 * Class Event to represent a Event Task. An Event
 * consists of a description and a time period.
 */
public class Event extends Task {
    /**
     * Creates a new Event with {@code description} and specified {@code time}.
     *
     * @param description the description/details of our task
     * @param time the time that the event happens
     */
    public Event(String description, String time) {
        super(description, time);
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

    /**
     * returns a String representation of an Event instance.
     *
     * @return String String representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString();
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
