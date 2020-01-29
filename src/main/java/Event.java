/**
 * Event
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class Event extends Task {
    String at;

    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    protected String getScheduledTime() {
        return this.at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)\n", super.toString(), this.at);
    }
}
