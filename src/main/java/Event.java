import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate atDate = null;
    private LocalTime atTime = null;

    public Event(String description, String atDateTime) {
        super(description, false);
        String[] rawDateTime = atDateTime.split(" ");
        String[] date = rawDateTime[0].split("/");
        if (date[0].length() < 2) {
            date[0] = "0" + date[0];
        }
        if (date[1].length() < 2) {
            date[1] = "0" + date[1];
        }
        String formattedDate = date[2] + "-" + date[1] + "-"
                + date[0];
        String formattedTime = "" + rawDateTime[1].charAt(0)
                + rawDateTime[1].charAt(1) + ":" + rawDateTime[1].charAt(2)
                + rawDateTime[1].charAt(3) + ":00";
        this.atDate = LocalDate.parse(formattedDate);
        this.atTime = LocalTime.parse(formattedTime);
    }

    public Event(String description, boolean isDone, String atDateTime) {
        super(description, isDone);
        String[] rawDateTime = atDateTime.split(" ");
        String[] date = rawDateTime[0].split("/");
        if (date[0].length() < 2) {
            date[0] = "0" + date[0];
        }
        if (date[1].length() < 2) {
            date[1] = "0" + date[1];
        }
        String formattedDate = date[2] + "-" + date[1] + "-"
                + date[0];
        String formattedTime = "" + rawDateTime[1].charAt(0)
                + rawDateTime[1].charAt(1) + ":" + rawDateTime[1].charAt(2)
                + rawDateTime[1].charAt(3) + ":00";
        this.atDate = LocalDate.parse(formattedDate);
        this.atTime = LocalTime.parse(formattedTime);
    }
    
    @Override
    public String getSaveRepresentation() {
        return "E|||" + getIsDone() + "|||" + getDescription() + "|||"
                + atDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy "))
                + atTime.format(DateTimeFormatter.ofPattern("kmm")) + "\n";
    }
    
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s, %s)", "E", (getIsDone() ? "\u2713" : "\u2718"), getDescription()
                , atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                , atTime.format(DateTimeFormatter.ofPattern("h:mma")));
    }
}