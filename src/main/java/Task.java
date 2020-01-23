public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void doTask() {
        isDone = true;
    }
    public String getStatusIcon() {
        return (isDone ? "Y" : "N");
    }

    public String getDescription() {
        return description;
    }
}
