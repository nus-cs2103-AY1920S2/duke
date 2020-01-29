public class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int done) {
        this.description = description;
        if (done == 1) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    public String toPrint() {
        if (this.isDone) {
            return "T | " + 1 + " | " + this.description;
        } else {
            return "T | " + 0 + " | " + this.description;
        }
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        if (this.isDone) {
            return "[✓] " + this.description;
        } else {
            return "[✗] " + this.description;
        }
    }
}