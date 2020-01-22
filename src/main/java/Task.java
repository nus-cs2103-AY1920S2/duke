public class Task {
    private String content;
    private boolean isDone;

    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public String getContent() {
        return this.content;
    }

    public String getIsDone() {
        if (this.isDone) {
            return "[\u2713] ";
        } else {
            return "[\u2718] ";
        }
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}