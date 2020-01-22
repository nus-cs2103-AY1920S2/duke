public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        if (at.equalsIgnoreCase("")) {
            throw new DukeException("No date");
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}