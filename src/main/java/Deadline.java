import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public final LocalDate date;
    public static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public Deadline(String name, String date) {
        super(name);
        this.date = LocalDate.parse(date, inputFormatter);
    }

    public Deadline(String name, boolean completed, String date) {
        super(name, completed); 
        this.date = LocalDate.parse(date, inputFormatter);
    }

    public Deadline(String name, boolean completed, LocalDate date) {
        super(name, completed);
        this.date = date;
    }

    @Override
    public boolean compareDate(LocalDate inputDate) {
        return this.date.equals(inputDate);
    }

    @Override
    public Deadline complete() {
        return new Deadline(this.name, true, this.date);
    }

    @Override
    public String storeFormat() {
        return "D| |" + completed + "| |" + name + "| |" + date.format(inputFormatter);
    }

    @Override
    public String toString() {
        String doneCheck = "[✓] ";
        String notDoneCheck = "[✗] ";

        if (completed) {
            return "[D]" + doneCheck + this.name + " (by: " + date.format(outputFormatter) + ")";
        } else {
            return "[D]" + notDoneCheck + this.name + " (by: " + date.format(outputFormatter) + ")";
        }
    }
}