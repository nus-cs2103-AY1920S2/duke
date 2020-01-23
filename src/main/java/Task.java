public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {}

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
        String toReturn = "";
        if (this instanceof Todo) {
            toReturn = "[T][" + getStatusIcon() + "] " + description;
        } else if (this instanceof Event) {
            toReturn = "[E][" + getStatusIcon() + "] " + description + " (at: " + ((Event) this).getDayTime() + ")";
        } else if (this instanceof Deadline) {
            toReturn = "[D][" + getStatusIcon() + "] " + description + " (by: " + ((Deadline) this).getDay() + ")";
        }
        return toReturn;
    }
}