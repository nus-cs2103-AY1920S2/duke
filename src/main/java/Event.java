public class Event extends Task {
    private String at;

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getFullDescription() {
        return "[E]" + super.getDescriptionWithIsDone() + " (at: " + this.at + ")";
    }
}