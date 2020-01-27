import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected static List<Task> taskList = new ArrayList<>(100);

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // return (isDone ? "[\u2713]" : "[\u2718]"); // return tick or X symbols
        return (isDone ? "[✓]" : "[✗]");    // the above did not work.
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
