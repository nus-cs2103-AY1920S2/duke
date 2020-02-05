package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime datetime;

    public Deadline(boolean isDone, String description, LocalDateTime datetime) {
        super(isDone, description);
        this.datetime = datetime;
    }

    public String toSaveFormat() {
        return String.format("D | %s | %s", super.toSaveFormat(), datetime);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}