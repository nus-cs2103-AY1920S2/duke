public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String toString() {
        if (this.isDone) {
            return "[T][✓] " + this.description;
        } else {
            return "[T][✗] " + this.description;
        }
    }
}