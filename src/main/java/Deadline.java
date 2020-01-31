import java.time.LocalDate;

/**
 * A Deadline task.
 */
public class Deadline extends DateTimeTask {
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public Task getCopy() {
        Deadline d = new Deadline(this.description, this.dateTime);
        if (this.isDone()) {
            d.setDone();
        }
        return d;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getDateTime() + ")";
    }
}
