package duke.task;

import duke.exceptions.InvalidArgumentException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public LocalDateTime by;

    public Deadline(String description, String by) throws InvalidArgumentException {
        super(description);
        try {
            DateTimeFormatter inputDTF = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime outputDT = LocalDateTime.parse(by, inputDTF);
            DateTimeFormatter outputDTF = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a");
            this.by = LocalDateTime.parse(outputDT.format(outputDTF), DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a"));
        } catch (DateTimeException ex) {
            throw new InvalidArgumentException();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a")) + ")";
    }
}