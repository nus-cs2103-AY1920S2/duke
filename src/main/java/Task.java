public abstract class Task {

    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String getStatus() {
        return isDone ? "V" : "X";
    }

    public int getStatusAsInt() {
        return isDone ? 1 : 0;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String generateSaveFileEntry();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), this.description);
    }
}
