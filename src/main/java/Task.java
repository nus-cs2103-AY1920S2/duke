import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected String type;
    protected LocalDate date;
    protected boolean isDone;
    protected ArrayList<Tag> tagList;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tagList = new ArrayList<>();
    }

    public Task(String description, ArrayList<Tag> tags) {
        this.description = description;
        this.isDone = false;
        this.tagList = tags;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    //Type should only be T(odo), D(eadline), or E(vent).
    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<Tag> getTagList() {
        return this.tagList;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String formatDate(LocalDate date) {
        return date.format(
                DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

}