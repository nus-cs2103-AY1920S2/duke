package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate endDate;

     public Deadline(String description, LocalDate endDate, boolean isDone) {
          super(description, isDone);
          this.endDate = endDate;
     }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
