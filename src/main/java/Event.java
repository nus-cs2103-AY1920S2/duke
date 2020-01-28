import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        super.type = Type.E;
        this.at = LocalDateTime.parse(at, inFormatter);
    }

    public String getDate() {
        return at.format(inFormatter);
    }

    public String toString() {
        return "[" + super.getType() + "]" + super.toString() + "(at: " + at.format(outFormatter) + ")";
    }

    public String saveString() {
        return getType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + getDate();
    }
}