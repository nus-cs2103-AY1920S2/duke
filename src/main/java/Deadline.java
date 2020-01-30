import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public String getTaskType() {
        return "D";
    }

<<<<<<< HEAD
    public String getTime() {
        return this.time;
=======
    public String formatDate() {
        DateTimeFormatter myformat = DateTimeFormatter.ofPattern("d MMM uuuu");
        String converted = this.date.format(myformat);
        return converted;
>>>>>>> branch-Level-8
    }

    @Override
    public String toString() {
        String myword = "";
        myword = myword + "[" + this.getTaskType() + "]"
                + " [" + super.getStatusIcon() + "] " + super.description +
                " (" + this.formatDate() + ")";

        return myword;
    }
}
