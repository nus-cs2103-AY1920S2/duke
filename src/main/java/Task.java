public abstract class Task {
    protected String description;
    protected String type;
    protected String date;
    protected boolean isDone;

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    //Type should only be T(odo), D(eadline), or E(vent).
    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public void markAsDone() {
        this.isDone = true;
    }

}
