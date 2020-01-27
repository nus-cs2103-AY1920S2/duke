import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
        protected LocalDateTime by;

        public Deadline(String task_name, LocalDateTime by) {
            super(task_name);
            this.by = by;
        }

        @Override
        public String toString() {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
            String formatDateTime = by.format(format);
            return "[D]" + super.toString() + " (by: " + formatDateTime + ")";
        }
    }
