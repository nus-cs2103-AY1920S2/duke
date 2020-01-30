public class Event extends Task {
    protected String timing;

    public Event(String command, String timing) {
        super(command);
        this.timing = timing;
    }

    public int getDoneInt() {
        return getDone() ? 1 : 0;
    }

    @Override
    public String updateFile() {
        return "E - " + getDoneInt() + " - " + getCommand() + "- " + timing;
    }

    @Override
    public String toString() {
        return "[E][" + getDoneSymbol() + "] " + getCommand() + "(at: " + timing + ")";
    }
}
