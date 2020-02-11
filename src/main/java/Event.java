import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends task {
    private String date;
    private String from;
    private LocalDate ld;

    public Event (String name, String date) {
        super(name);
        String[] temp = date.split(" ");
        this.date = date;
        this.ld = LocalDate.parse(temp[0]);
        this.from = temp[1];
    }

    @Override
    public String toSave() {
        if (this.isDone()) {
            return "E /n 1 /n " + this.getName() + " /n " + this.date;
        } else {
            return "E /n 0 /n " + this.getName() + " /n " + this.date;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + ld.getDayOfMonth() + " " + ld.getMonth() + " " + ld.getYear() + " " + from + ")";
    }
}
