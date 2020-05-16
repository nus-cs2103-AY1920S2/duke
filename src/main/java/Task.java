public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean hasDone() {
        return isDone;
    }

    public String getDoneIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public abstract String getDisplayName();

    public abstract String getType();

    public String getStatus() {
        return name + "[" + getDoneIcon() + "]";
    }

    public abstract String getString();
}
