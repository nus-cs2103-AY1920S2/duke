import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String connector;
    protected LocalDate datetime;

    public Deadline(String description, String connector, String datetime, boolean isDone) {
        super(description, isDone);
        this.connector = connector;
        this.datetime = LocalDate.parse(datetime);
    }

    public Deadline(String description, String connector, String datetime) {
        this(description, connector, datetime, false);
    }

    public String getFileString() {
        return "D|" + isDone + "|" + description + "|" + connector + "|" + datetime;
    }

    @Override
    public String toString() {
        return ("[D][" + getStatusIcon() + "] " + description + " (" + connector + ": " + this.getDatetime() + ")");
    }

    public String getDatetime() {
        return this.datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
