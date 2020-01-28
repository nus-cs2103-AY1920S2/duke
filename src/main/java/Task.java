import javax.xml.stream.events.DTD;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    public static DateTimeFormatter parser = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy ha");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //return (isDone ? "\u2713" : "\u2718"); //return tick or cross symbols
        return (isDone ? "Y" : "N"); //return Y or N symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString() { return "[" + getStatusIcon() + "] " + this.description; }

    public abstract String fileString();
}