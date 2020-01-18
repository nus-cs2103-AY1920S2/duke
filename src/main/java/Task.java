public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void completeTask() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        String indicator;
        if(isDone) {
            indicator = "✓";
        } else {
            indicator = "✗";
        }

        return String.format("[%s] %s", indicator, this.description);
    }
}

