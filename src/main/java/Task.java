public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (isDone ? "Done" : "Not done");
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String toReturn = "";
        if (this instanceof Todo) {
            toReturn = "[T][" + getStatus() + "] " + description;
        } else if (this instanceof Event) {
            toReturn = "[E][" + getStatus() + "] " + description + " (at: " + ((Event) this).getDayTime() + ")";
        } else if (this instanceof Deadline) {
            toReturn = "[D][" + getStatus() + "] " + description + " (by: " + ((Deadline) this).getDay() + ")";
        }
        return toReturn;
    }
}