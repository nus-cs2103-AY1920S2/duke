import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String connector;
    protected LocalDate datetime;

    public Deadline(String description, String connector, String datetime) {
        super(description);
        this.description = description;
        this.connector = connector;
        this.datetime = LocalDate.parse(datetime);
    }

    @Override
    public String toString() {
        return ("[D][" + getStatusIcon() + "] " + description + " (" + connector + ": " + this.getDatetime() + ")");
    }

    public String getDatetime() {
        return this.datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
