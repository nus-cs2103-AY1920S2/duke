import java.time.LocalDate;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    LocalDate by;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.by = time;
    }

    /**
     * Returns deadline task in string format with a proper date format.
     * @return string format to save in storage.
     */

    @Override
    public String saveFormat() {
        return "D" + super.saveFormat() + "," + by;
    }

    /**
     * Returns deadline task in string format with a proper date format.
     * @return string format to show user.
     */

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.getMonth() + " " + by.getDayOfMonth() + " " + by.getYear() + ")";
    }
}
