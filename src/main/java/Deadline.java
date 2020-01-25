import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public Deadline(int id, String task, String date) throws BadDateException {
        super(id, task);
        this.date = Parser.dateParser(date);
    }

    @Override
    public String toString() {
        if (done) return  "[D][✓] " + task + " (by: " +
                date.format(Parser.DATE_FORMATTER) + ")";
        else return "[D][✗] " + task + " (by: " +
                date.format(Parser.DATE_FORMATTER) + ")";
    }
}
