import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends AbstractTask {
    LocalDate date;
    String time;
    String preposition;

    /**
     * Constructor for Deadline class.
     * @param taskName Name or description of the deadline
     * @param preposition English word string to indicate the date clearly (eg. at, by, before, between)
     * @param date Date of deadline
     */
    public Deadline(String taskName, String preposition, LocalDate date) {
        super(taskName);
        this.preposition = preposition;
        this.date = date;
    }

    /**
     * Constructor for Deadline class with additional info provided after date taken to be time.
     * @param taskName Name or description of the deadline
     * @param preposition English word string to indicate the date clearly (eg. at, by, before, between)
     * @param date Date of deadline
     * @param time Time of deadline
     */
    public Deadline(String taskName, String preposition, LocalDate date, String time) {
        super(taskName);
        this.preposition = preposition;
        this.date = date;
        this.time = time;
    }

    private String formattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return (this.time != null) ? "[D]" + taskStateString() + " " + this.taskName + " " + this.preposition + " "
                + formattedDate() + " " + this.time
                : "[D]" + taskStateString() + " " + this.taskName + " " + this.preposition + " " + formattedDate();
    }

}
