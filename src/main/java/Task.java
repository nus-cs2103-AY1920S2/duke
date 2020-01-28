import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Task {
    protected String description;
    protected boolean isDone;
    protected DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy KK:mm a");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]": "[\u2718]"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
