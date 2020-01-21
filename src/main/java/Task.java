public class Task {
    private String content;
    private boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[\u2713] " + content;
        } else {
            return "[\u2718] " + content;
        }
    }
}
