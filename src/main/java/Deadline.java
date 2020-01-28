import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter inputDTF = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime outputDT = LocalDateTime.parse(by, inputDTF);
        DateTimeFormatter outputDTF = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a");
        this.by = LocalDateTime.parse(outputDT.format(outputDTF), DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a")) + ")";
    }
}