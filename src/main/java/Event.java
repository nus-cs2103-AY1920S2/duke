/**
 * Class that represents
 * "Event" type Tasks
 */
public class Event extends Task {
    public Event(String td) {
        super(td);
    }

    @Override
    public String type() {
        return "E";
    }
}
