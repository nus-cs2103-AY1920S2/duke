import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String description, String at) {
        super(description);
<<<<<<< HEAD
        super.type = Type.E;
        this.at = at;
=======
        this.at = LocalDateTime.parse(at, inFormatter);
>>>>>>> branch-Level-8
    }

    public String getDate() {
        return at;
    }

    public String toString() {
<<<<<<< HEAD
        return "[" + super.getType() + "]" + super.toString() + "(at: " + at + ")";
    }

    public String saveString() {
        return getType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + getDate();
=======
        return "[E]" + super.toString() + "(at: " + at.format(outFormatter) + ")";
>>>>>>> branch-Level-8
    }
}