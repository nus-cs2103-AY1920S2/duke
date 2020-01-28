import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Event extends Task{
    protected LocalDate date;

    public Event(String command, String date) {
        super(command);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toStringTxt() {
<<<<<<< HEAD:src/main/java/task/Event.java
        return "E2/" + super.getIcon() + "/" + command + "/" + date + "hello";
=======
        return "E/" + super.getIcon() + "/" + command + "/" + date + "\n";
>>>>>>> parent of c4815cf... Added packages:src/main/java/Event.java
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
