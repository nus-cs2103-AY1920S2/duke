import java.time.LocalDate;

public class Event extends Task {
    private LocalDate date;

    public Event(int id, String task, String date) {
        super(id, task);
        this.date = Parser.dateParser(date);
    }

    @Override
    public String toString() {
        if (done) return  "[E][✓] " + task + " (at: " +
                date.format(Parser.DATE_FORMATTER) + ")";
        else return "[E][✗] " + task + " (at: " +
                date.format(Parser.DATE_FORMATTER) + ")";
    }
}
