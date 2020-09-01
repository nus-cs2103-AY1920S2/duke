public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return 'Tick' if done else 'X' symbol
    }

    public String toString(){
        return "[" + getStatusIcon() +"]" + " " + description;
    }
}
