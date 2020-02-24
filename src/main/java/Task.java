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

    public String getContent() {
        return this.content;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateDate(String date) {}

    public String toStore() {
        if (isDone) {
            return "[O] " + content;
        } else {
            return "[X] " + content;
        }
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[O] " + content;
        } else {
            return "[X] " + content;
        }
    }
}
