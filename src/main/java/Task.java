import java.io.Serializable;
//Adapted from https://nus-cs2103-ay1920s2.github.io/website/schedule/week2/project.html
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    
    private Task() {
    }
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    
    public String description() {
        return description;
    }
    
    public boolean isDone() {
        return isDone;
    }
    
    public void markAsDone() {
        isDone = true;
    }
}
