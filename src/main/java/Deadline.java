import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
<<<<<<< HEAD
        super.type = Type.D;
        this.by = by;
=======
        this.by = LocalDateTime.parse(by, inFormatter);
>>>>>>> branch-Level-8
    }

    public String getDate() {
        return by;
    }

    public String toString() {
<<<<<<< HEAD
        return "[" + super.getType() + "]" + super.toString() + "(by: " + by + ")";
    }

    public String saveString() {
        return getType() + " | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + getDate();
=======
        return "[D]" + super.toString() + "(by: " + by.format(outFormatter) + ")";
>>>>>>> branch-Level-8
    }
}
