import java.time.LocalDate;

/** Event is a child class of Task.
 * Task that starts at a specific time and ends at a specific time.*/
public class Event extends Task {
    String dateString;

    /**
     * Constructor that takes in 2 params.
     *
     * @param name Name of Event
     * @param dateString Date of event
     */
    public Event(String name, String dateString) {
        super(name);
        this.dateString = dateString;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getClassName() {
        return "event";
    }

    /**
     * Converts Event into a String to be saved to file.
     * @return String to be saved to file
     */
    @Override
    public String toFile() {
        int doneInt = done ? 1 : 0;
        return "E , " + doneInt + " , " + name + " , " + dateString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + dateString + ")";
    }

}
