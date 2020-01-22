public class Event extends Task{
    protected String noun;
    protected String timing;

    public Event(String description, String noun, String timing) {
        super(description);
        this.noun = noun;
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + noun + ":" + timing + ")";
    }
}
