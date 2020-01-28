import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    String by;
    String i;
    LocalDate date;

    public Deadline(String deadline, String by) {
        super(deadline);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    public Deadline(String deadline, String by, String i) {
        super(deadline);
        this.i = i;
        this.by = by;
        if (i.equals("1")) {
            this.doneStatus = true;
        } else {
            this.doneStatus = false;
        }
        this.date = LocalDate.parse(by);
    }

    @Override
    public String save() {
        int myInt = doneStatus ? 1 : 0;
        return "D , " + myInt + " , " + taskName + " , " + by;
    }

    @Override
    public String toString() {
        String formattedBy = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}