import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

//    protected String by;
    protected LocalDate date;

    public Deadline (String description, LocalDate date) {
        super (description);
        this.date = date;
    }

    @Override
    public String saveFile() {
        if (this.status.equals("Done")) {
            return  "D|1||" + this.description + "|||" + this.date;
        } else {
            return  "D|0||" + this.description + "|||" + this.date;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
