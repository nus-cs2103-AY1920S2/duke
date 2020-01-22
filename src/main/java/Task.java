public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return tick or X symbols
    }

    @Override
    public String toString() {
        String temp = "[" + getStatusIcon() + "] " + description;
        return temp;
    }
}