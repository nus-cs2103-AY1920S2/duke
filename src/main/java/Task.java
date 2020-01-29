public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task createTask(String[] commandArgs) throws IllegalArgumentException {return null;};

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.getDescription();
    }

    // Override hashcode if you get the time!
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task t = (Task) o;

        return t.description.equals(this.description) && (Boolean.compare(t.isDone, this.isDone)==0);
    }

    // Getter
    public String getDescription() {
        return this.description;
    }
}
