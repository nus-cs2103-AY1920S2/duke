import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String time) {
        // precondition: time in yyyy-mm-dd format
        this.description = description;
        this.isDone = false;
        this.time = LocalDate.parse(time);
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public LocalDate getDate() {
        return time;
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getTypeName() {
        return "Task";
    }

    @Override
    public String toString() {
        String timeOptional = (time.equals("")) ? "" : ", " + time;
        return "[" + getStatusIcon() + "]" + " " + description + timeOptional;
    }

    public String toStringFile() {
        int isDoneInt = (isDone) ? 1 : 0;
        String timeOptional = (time.equals(LocalDate.parse("2099-12-31"))) ? "" : ", " + getTime();
        return isDoneInt + " | " + description + timeOptional;
    }
}