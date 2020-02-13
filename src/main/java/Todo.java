public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = "T";
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
