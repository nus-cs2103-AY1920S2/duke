import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {

    private LocalDate date;

    Deadline(String taskDescription, LocalDate date) {
        super(taskDescription);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
