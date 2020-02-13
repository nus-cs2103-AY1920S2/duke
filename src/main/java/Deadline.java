import java.time.LocalDate;

public class Deadline extends Task {
    public Deadline(String description, LocalDate date) {
        super(description);
        this.type = "D";
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s (%s)",
                this.getType(),
                this.getStatusIcon(),
                this.getDescription(),
                this.formatDate(getDate()));
    }
}
