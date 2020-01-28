import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime date;

    public Deadline(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    public Deadline(String name, LocalDateTime date, boolean isDone) {
        super(name, isDone);
        this.date = date;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:ss a")) + ")";
    }

    public String writeDrive() {
        return "D|" + (super.isDone()? "1|" : "0|") + this.name + "|" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHss"));
    }

    public Deadline setDone() {
        return new Deadline(this.name, this.date, true);
    }
}
