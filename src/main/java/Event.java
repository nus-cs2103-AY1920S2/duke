public class Event extends Task {

    protected String on;

    public Event(String description, String on) {
        super(description);
        this.on = on;
    }

    @Override
    public String toString() {
        String[] date = on.split(" ", 2);
        return "[E]" + super.toString() + " (" + date[0] + ": " + date[1] + ")";
    }
}