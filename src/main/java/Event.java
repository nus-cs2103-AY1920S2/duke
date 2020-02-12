public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toSaveForm() {
        return "E , " + super.getStatusIcon() + " , " + description + " , " + at + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
