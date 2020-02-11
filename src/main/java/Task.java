public class Task {
    protected String type;
    protected String description;
    protected boolean isDone;

    public Task(String type, boolean isDone, String description) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    public String getType() {
        return this.type;
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

    public String[] toDataTokens() {
        String isDoneString = String.valueOf(this.isDone);
        return new String[] {isDoneString, this.description};
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}