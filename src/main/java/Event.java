import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
        protected LocalDateTime at;

        public Event(String task_name, LocalDateTime at) {
            super(task_name);
            this.at = at;
        }

        @Override
        public String toString() {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm");
            String formatDateTime = at.format(format);
            return "[E]" + super.toString() + " (at: " + formatDateTime + ")";
        }
    }