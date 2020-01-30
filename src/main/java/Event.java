import java.time.LocalDate;

public class Event extends Task {
    String dateString;

    public Event(String name, String dateString) {
        super(name);
        this.dateString = dateString;
    }

    @Override
    public String toFile() {
        int doneInt = done ? 1 : 0;
        return "E , " + doneInt + " , " + name + " , " + dateString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + dateString + ")";
    }
}
