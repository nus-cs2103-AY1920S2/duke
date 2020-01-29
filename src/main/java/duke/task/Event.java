package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        DateTimeFormatter inputDTF = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime outputDT = LocalDateTime.parse(at, inputDTF);
        DateTimeFormatter outputDTF = DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a");
        this.at = LocalDateTime.parse(outputDT.format(outputDTF), DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMMM yyyy, h:mm a")) + ")";
    }
}