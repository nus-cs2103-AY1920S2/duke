package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeParser {
    private LocalDateTime time;
    private String timeString;

    public TimeParser(String timeString) {
        this.timeString = timeString;
        format();
    }

    private void format() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            time = LocalDateTime.parse(timeString, dtf);
        } catch (DateTimeParseException dtpe) {
            System.out.println(dtpe);
        }
    }

    public LocalDateTime getTime() {
        return this.time;
    }
}

