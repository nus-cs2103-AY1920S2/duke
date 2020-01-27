import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
        protected LocalDateTime by;

        public Deadline(String task_name, LocalDateTime by,int done) {
            super("deadline",done,task_name);
            this.by = by;
        }

        LocalDateTime getBy(){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            String formatDateTime = by.format(format);
            return by;
        }

        @Override
        public String toString() {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
            String formatDateTime = by.format(format);
            return "[D]" + super.toString() + " (by: " + formatDateTime + ")";
        }
    }
