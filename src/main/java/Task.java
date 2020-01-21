public class Task {
    private String content;
    private boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public String getStatus() {
        if (isDone) {
            return "[\u2713] " + content;
        } else {
            return "[\u2718] " + content;
        }
    }

    public void setDone() {
        this.isDone = true;
    }
}
