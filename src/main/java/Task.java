public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); //return Y or N symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toSaveName() {
        if (this.isDone) {
            return " | 1 | " + this.description;
        } else {
            return " | 0 | " + this.description;
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }

}
