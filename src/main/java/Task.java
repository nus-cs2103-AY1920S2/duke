public class Task {
    protected String description;
    protected boolean isDone;

    public Task(){ // default constructor with no parameters

    }

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[✔]" : "[✘]"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

}
