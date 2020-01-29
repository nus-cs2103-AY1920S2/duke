public class Task {
    protected TaskType TYPE;
    protected String description;
    protected boolean isDone;

    public Task(){ // default constructor with no parameters

    }

    public Task(String description) { // constructor for Dude class TODO need to change
        this.description = description.trim();
        this.isDone = false;
    }

    public Task(String status, String description) {
        this.isDone = status.equals("1");
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "[✔]" : "[✘]"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public int getDoneInt() {
        return (isDone ? 1 : 0);
    }

    public TaskType getTaskType() {
        return this.TYPE;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

}
