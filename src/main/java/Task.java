public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        //empty
    }

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

    public abstract String getType();

    public abstract String getTask();

    public abstract String getDate();

    public abstract String getWord();

    public abstract String getDescription();

    public int getDone() {
        if (isDone == true) {
            return 1;
        }
        else {
            return 0;
        }
    }
}