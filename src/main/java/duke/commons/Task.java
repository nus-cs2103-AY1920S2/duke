package duke.commons;
import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> tagList;

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
        this.tagList = new ArrayList<>();
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void addTag(String tag) {
        this.tagList.add(tag);
    }

    public String[] toDataTokens() {
        String isDoneString = String.valueOf(this.isDone);
        return new String[] {isDoneString, this.description};
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}