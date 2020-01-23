public class Event extends Task {
    protected String timePeriod;

    public Event(String identifier, String timePeriod) {
        super(identifier);
        this.timePeriod = timePeriod;
    }

    @Override
    public String toString() {
        return "Event: " + super.toString() + " (at: " + timePeriod + ")";
    }
}
