//Adapted from https://nus-cs2103-ay1920s2.github.io/website/schedule/week2/project.html
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    
    public void markAsDone() {
        isDone = true;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), description);
    }
}
