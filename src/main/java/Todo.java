public class Todo extends Task {
    public Todo(String description) {
        this.description = description;
        this.type = "T";
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s][%s] %s",
                this.getType(),
                this.getStatusIcon(),
                this.getDescription());
    }
}
