public class EventTask extends Task {

    private String at;

    public EventTask(String description, String at) throws InvalidDukeFormatException {
        super(description);
        this.at = at;

        if (at.isEmpty()) {
            throw new InvalidDukeFormatException("Missing /at clause or missing at when!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + this.at + ")";
    }

    @Override
    public String format() {
        return "E | " + super.format() + " | " + this.at;
    }
}
