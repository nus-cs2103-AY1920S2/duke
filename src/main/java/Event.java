public class Event extends Task {
    protected String timing;

    public Event(String command, String timing) {
        super(command);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E][" + getDoneSymbol() + "] " + getCommand() + "(at: " + timing + ")";
    }
}
