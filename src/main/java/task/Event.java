package task;

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
        return "E/" + super.getIcon() + "/" + command + "/" + date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
