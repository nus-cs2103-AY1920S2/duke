public class Event extends Task {
    private String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    public Event(String name, String at, boolean isDone) {
        super(name, isDone);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String getMnemonic() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getMnemonic() + "]" + super.toString() + " (at: " + at +  ")";
    }
}
