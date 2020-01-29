import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends TaskDate {

    protected LocalDate date;
    protected boolean isTime;
    protected LocalTime time;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        isTime = false;
    }

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
        isTime = true;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String formattedDate = (this.date).format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        if (isTime) {
            String formattedTime = (this.time).format(DateTimeFormatter.ofPattern("h:mma"));
            return "[D]" + super.toString() + " (by:" + formattedDate + " " + formattedTime + ")";
        } else {
            return "[D]" + super.toString() + " (by:" + formattedDate + ")";
        }
    }
}

