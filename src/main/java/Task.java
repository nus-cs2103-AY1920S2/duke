public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "O" : "X");
    }

    public String getDescription(){
        return this.description;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public abstract String toParser();
}