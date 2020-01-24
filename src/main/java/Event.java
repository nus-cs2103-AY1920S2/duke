import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate date;
    protected LocalTime time;
    public String taskType = "E";

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String status, String description, String at) {
        super(description);
        this.setStatus(status);
        this.at = at;
    }

    public void setDateTime() {
        String[] b = this.at.split(" ");
        if (b.length > 1) {
            this.date = LocalDate.parse(b[0]);
            this.time = LocalTime.parse(b[1]);
        } else {
            this.date = LocalDate.parse(this.at);
            this.time = null;
        }
    }

    public String dateToString() {
        setDateTime();
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String timeToString() {
        setDateTime();
        return this.time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        //return "[E]" + super.toString() + "(at: " + at + ")";
        String[] b = this.at.split(" ");
        if (b.length > 1) {
            return "[E]" + super.toString() + "(at: " + dateToString() + " " + timeToString()+ ")";
        } else {
            return "[E]" + super.toString() + "(at: " + dateToString() + ")";
        }
    }

    @Override
    public String saveString() {
        //return "E | " + super.saveString() + "| " + at;
        String[] b = this.at.split(" ");
        if (b.length > 1) {
            return "E | " + super.saveString() + "| " + dateToString() + " " + timeToString();
        } else {
            return "E | " + super.saveString() + "| " + dateToString();
        }
    }
}
