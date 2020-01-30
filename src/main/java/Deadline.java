import java.time.LocalDate;

public class Deadline extends DateTimeTask {
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getDateTime() + ")";
    }
}
