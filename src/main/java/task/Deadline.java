package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dueDate;

    public Deadline(String identifier, String dueDate) throws DateTimeParseException {
        super(identifier);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:m");
        this.dueDate = LocalDateTime.parse(dueDate, formatter);
    }

    @Override
    public String toString() {
        return "Deadline: " + super.toString() + " (please complete by " + dueDate + ")";
    }
}
