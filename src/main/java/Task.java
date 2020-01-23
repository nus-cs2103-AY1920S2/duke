public class Task {
    protected String description;
    protected boolean isDone;
    protected int entrynum;

    public Task(String description, int entrynum) {
        this.description = description;
        this.isDone = false;
        this.entrynum = entrynum;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void makeDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
