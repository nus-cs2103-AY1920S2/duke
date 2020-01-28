public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        super.type = Type.E;
        this.at = at;
    }

    public String getDate() {
        return at;
    }

    public String toString() {
        return "[" + super.getType() + "]" + super.toString() + "(at: " + at + ")";
    }

    public String saveString() {
        return getType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + getDate();
    }
}