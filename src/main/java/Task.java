public class Task {
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void finishTask() {
        this.done = true;
    }

    public boolean getStatus() {
        return this.done;
    }

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        if (done) {
            return String.format("[^] %s", this.task);
        } else {
            return String.format("[x] %s", this.task);
        }
    }

    public String toFormatString() {
        return "";
    }
}
