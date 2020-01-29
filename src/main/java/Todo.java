public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int done) {
        super(description, done);
    }

    public String toPrint() {
        if (this.isDone) {
            return "T | " + 1 + " | " + this.description;
        } else {
            return "T | " + 0 + " | " + this.description;
        }
    }

    public String toString() {
        if (this.isDone) {
            return "[T][✓] " + this.description;
        } else {
            return "[T][✗] " + this.description;
        }
    }
}