import java.time.LocalDate;

public class Deadline extends Task{
    public Deadline(String description, String date) {
        this.description = description;
        this.type = "D";
        this.date = parseDate(date);
        this.isDone = false;
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
