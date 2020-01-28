public class Task {
    protected String description;
    protected boolean isDone;
    protected Type type;
    protected String date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = Type.X;
        this.date = "";
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]": "[\u2718]"); //return tick or X symbols
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDate() {
        return date;
    }

    public Type getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    public String saveString() {
        return type + " | " + (isDone ? "1" : "0") + " | " + description + " | " + date;
    }
}
